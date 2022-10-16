import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TodoListComponent} from './todo-list/todo-list.component';
import {TodoService} from "./service/todo.service";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from '@angular/forms';
import {TodoFormComponent} from './todo-form/todo-form.component';

@NgModule({
  declarations: [
    AppComponent,
    TodoListComponent,
    TodoFormComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [TodoService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
