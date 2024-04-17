import { Component } from '@angular/core';

import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-courseform',
  templateUrl: './courseform.component.html',
  styleUrls: ['./courseform.component.css']
})
export class CourseformComponent {

  courseArray: any ;

  id: string ="";
  courseName: string="";
  courseLevel: string="";
  courseDomain: string="";
  courseDuration: number =0;
  dateAdded: Date = new Date();
  coursePrice: number = 0;
  courseDescription: string="";

  constructor(private http: HttpClient) {
    this.GetAllcourse();
  }

  ngOnInit(): void {}

  GetAllcourse() {
    this.http.get("http://localhost:8090/pi/courses/retrieve-courses").subscribe((resultData: any) => {
      console.log(resultData);
      this.courseArray = resultData;


    
    });
  }


  
  add() {
    let bodyData = {
      "courseName": this.courseName,
      "courseLevel": this.courseLevel,
      "dateAdded": this.dateAdded,
      "courseDuration": this.courseDuration,
      "courseDomain":this.courseDomain , 
      "coursePrice":this.coursePrice , 
      "courseDescription":this.courseDescription

    };

    this.http.post("http://localhost:8090/pi/courses/add-course", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("course added Successfully");

    });
  }


  

}
