import { Component } from '@angular/core';

@Component({
  selector: 'app-courseform',
  templateUrl: './courseform.component.html',
  styleUrls: ['./courseform.component.css']
})
export class CourseformComponent {
  id: string ="";
  courseName: string="";
  courseLevel: string="";
  courseDomain: string="";
  courseDuration: number =0;
  dateAdded: Date = new Date();
  coursePrice: number = 0;
  courseDescription: string="";

  

}
