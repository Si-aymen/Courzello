import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {CoursesComponent} from "./courses/courses.component";
import {TeachersComponent} from "./teachers/teachers.component";
import { CourseformComponent } from './courseform/courseform.component';
import { ClassroomComponent } from './classroom/classroom.component';
import { ClassroomDetailsComponent } from './classroom-details/classroom-details.component';
import { CourseDetailsComponent } from './course-details/course-details.component';
import { SelectCourseComponent } from './select-course/select-course.component';

const routes: Routes = [
  {path:"", redirectTo:"home", pathMatch:"full"},
  {path: "home", component:HomeComponent},
  {path: "courses", component:CoursesComponent},
  {path: "teachers", component:TeachersComponent},
  {path: "addcourse", component:CourseformComponent},
  {path: "classroom", component:ClassroomComponent},
  {
    path: 'Classroom/Details/:id', 
    component: ClassroomDetailsComponent, 
  },

  {
    path: 'courseDetails/:id', 
    component: CourseDetailsComponent, 
  },

  {
    path: 'selectCourse/:id', 
    component: SelectCourseComponent, 
  },




  {path:"**", component:HomeComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
