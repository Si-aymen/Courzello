import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {CoursesComponent} from "./courses/courses.component";
import {TeachersComponent} from "./teachers/teachers.component";

const routes: Routes = [
  {path:"", redirectTo:"home", pathMatch:"full"},
  {path: "home", component:HomeComponent},
  {path: "courses", component:CoursesComponent},
  {path: "teachers", component:TeachersComponent},
  





  {path:"**", component:HomeComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
