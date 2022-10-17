import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TodoService} from "../../service/todo.service";
import {UserService} from "../../service/user.service";
import {User} from "../../model/user";
import {TodoDto} from "../../model/todo-dto";
import {Todo} from "../../model/todo";

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.css']
})
export class TodoFormComponent implements OnInit {
  todoDto: TodoDto
  users: User[]
  updating: boolean

  constructor(private route: ActivatedRoute,
              private router: Router,
              private todoService: TodoService,
              private userService: UserService) {
    this.todoDto = new TodoDto()
  }

  ngOnInit(): void {
    const todoEdit: Todo = history.state.todo

    if (todoEdit != undefined) {
      this.updating = true

      this.todoDto.completed = todoEdit.completed
      this.todoDto.title = todoEdit.title
      this.todoDto.id = todoEdit.id
      this.todoDto.userId = todoEdit.user.id
    }

    this.userService.findAll().subscribe(data => {
      this.users = data
    });
  }

  onSubmit() {
    if (this.updating) {
      this.todoService.update(this.todoDto).subscribe(() => this.gotoTodoList())
    } else {
      this.todoService.insert(this.todoDto).subscribe(() => this.gotoTodoList())
    }
  }

  gotoTodoList() {
    this.router.navigate(['/todo'])
  }

}
