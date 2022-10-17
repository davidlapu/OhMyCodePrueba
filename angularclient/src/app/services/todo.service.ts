import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Todo} from '../models/todo';
import {Observable} from 'rxjs';
import {TodoDto} from "../models/todo-dto";

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  private readonly todosUrl: string

  constructor(private http: HttpClient) {
    this.todosUrl = 'http://localhost:8080/todo'
  }

  public get(page: number, sortingParam: string, sortingDirection: string, title?: string, username?: string): Observable<Todo[]> {
    let params = new HttpParams()
    params = params.append('page', page)
    params = params.append('sortingParam', sortingParam)
    params = params.append('sortingDirection', sortingDirection)
    if (title != undefined) {
      params = params.append('title', title)
    }
    if (username != undefined && username != '') {
      params = params.append('username', username)
    }

    return this.http.get<Todo[]>(this.todosUrl, {params: params})
  }

  public insert(todoDto: TodoDto) {
    return this.http.post<TodoDto>(this.todosUrl, todoDto)
  }

  public update(todoDto: TodoDto) {
    return this.http.put<TodoDto>(this.todosUrl, todoDto)
  }

  public delete(id: string) {
    let params = new HttpParams()
    params = params.append('id', id)

    return this.http.delete(this.todosUrl, {params: params})
  }

}
