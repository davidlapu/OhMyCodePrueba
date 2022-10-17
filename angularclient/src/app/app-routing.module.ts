import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TodoListComponent} from "./components/todo-list/todo-list.component";
import {TodoFormComponent} from "./components/todo-form/todo-form.component";

const routes: Routes = [
  {path: 'todo', component: TodoListComponent},
  {path: 'add-todo', component: TodoFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
