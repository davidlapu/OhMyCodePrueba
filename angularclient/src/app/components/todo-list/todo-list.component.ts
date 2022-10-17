import {Component, OnInit} from '@angular/core';
import {Todo} from '../../model/todo';
import {TodoService} from "../../service/todo.service";
import {Router} from '@angular/router';

@Component({
  selector: 'app-todo-list', templateUrl: './todo-list.component.html', styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {
  idTodoDelete: string
  sortingParam = 'title'
  page = 0
  todos: Todo[];
  filters = {
    title: '', username: ''
  }
  private sortingDirection: string = 'asc'
  private readonly PAGE_SIZE = 10;

  constructor(private todoService: TodoService, private router: Router) {
  }

  ngOnInit(): void {
    this.reloadTodos()
  }

  openEditForm(todo: Todo): void {
    this.router.navigate(["add-todo"], {state: {todo}})
  }

  deleteTodo(todoId: string) {
    this.todoService.delete(todoId).subscribe(() => {
      this.reloadTodos()
    });
  }

  reloadTodos() {
    this.todoService.get(this.page, this.sortingParam, this.sortingDirection, this.filters.title, this.filters.username).subscribe(data => {
      this.todos = data
    })
  }

  switchSortingDirection() {
    if (this.sortingDirection == 'asc') {
      this.sortingDirection = 'desc'
    } else {
      this.sortingDirection = 'asc'
    }
  }

  switchDirectionAndSort(sortingParam: string) {
    this.sortingParam = sortingParam
    this.switchSortingDirection()
    this.reloadTodos()
  }

  nextPage() {
    if (this.todos.length == this.PAGE_SIZE) {
      this.page = this.page + 1
      this.reloadTodos()
    }
  }

  prevPage() {
    if (this.page > 0) {
      this.page = this.page - 1
      this.reloadTodos()
    }
  }
}
