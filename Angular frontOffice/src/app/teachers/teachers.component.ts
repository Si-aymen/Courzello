import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent {

  teacherArray: any[] = [];
  id: String = "";
  login: String = "";
  password: string = "";
  firstName: string ="";
  lastName : string = "" ;
  email : string ="";
  dateOfBirth : Date =new Date();
  speciality: string = "";



  constructor(private http: HttpClient) {
    this.GetAllTeachers();
  }

  ngOnInit(): void {}

  GetAllTeachers() {
    this.http.get("http://localhost:8090/pi/users/teachers").subscribe((resultData: any) => {
      console.log(resultData);
      this.teacherArray = resultData;



    });
  }

}
