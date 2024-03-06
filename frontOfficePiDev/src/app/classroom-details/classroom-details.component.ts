import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute,Router } from '@angular/router';


@Component({
  selector: 'app-classroom-details',
  templateUrl: './classroom-details.component.html',
  styleUrls: ['./classroom-details.component.css']
})
export class ClassroomDetailsComponent implements OnInit {  
  courseArray: any ;

  id: string ="";
  classroomId: string ="";
  courseName: string="";
  courseLevel: string="";
  courseDomain: string="";
  courseDuration: number =0;
  dateAdded: Date = new Date();
  coursePrice: number = 0;
  courseDescription: string="";

  constructor(private http: HttpClient, private route: ActivatedRoute) {
  
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.classroomId = params['id']; 
      console.log(this.classroomId);
      this.GetAllcourse();



    });
  }
  GetAllcourse() {
    this.http.get( "http://localhost:8090/pi/courses/classroom/"+ this.classroomId ).subscribe((resultData: any) => {
      console.log(resultData);
      this.courseArray = resultData;
      
    });
  }

  


}
