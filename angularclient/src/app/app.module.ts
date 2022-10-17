import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TodoListComponent} from './components/todo-list/todo-list.component';
import {TodoService} from "./service/todo.service";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from '@angular/forms';
import {TodoFormComponent} from './components/todo-form/todo-form.component';
import {UserService} from "./service/user.service";

@NgModule({
  declarations: [
    AppComponent,
    TodoListComponent,
    TodoFormComponent,
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [TodoService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
