import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { CoursesComponent } from './courses/courses.component';
import { TeachersComponent } from './teachers/teachers.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { CourseformComponent } from './courseform/courseform.component';
import { FormsModule } from '@angular/forms';
import { ClassroomComponent } from './classroom/classroom.component';
import { ClassroomDetailsComponent } from './classroom-details/classroom-details.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CoursesComponent,
    TeachersComponent,
    CourseformComponent,
    ClassroomComponent,
    ClassroomDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule ,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
