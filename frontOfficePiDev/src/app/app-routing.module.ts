import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {CoursesComponent} from "./courses/courses.component";

const routes: Routes = [
  {path:"", redirectTo:"home", pathMatch:"full"},
  {path: "home", component:HomeComponent},
  {path: "courses", component:CoursesComponent},





  {path:"**", component:HomeComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
