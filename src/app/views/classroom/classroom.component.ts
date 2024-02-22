import { Component, OnInit } from '@angular/core';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';



interface IUser {
  name: string;
  state: string;
  registered: string;
  country: string;
  usage: number;
  period: string;
  payment: string;
  activity: string;
  avatar: string;
  status: string;
  color: string;
}

@Component({
  selector: 'app-classroom',
  templateUrl: './classroom.component.html',
  styleUrls: ['./classroom.component.scss']
})


export class ClassroomComponent {


  classroomArray : any[] = [] ;
  classroomCapacity : number =0 ;  
  classroomName : String ="" ;  
  classroomLvl : String ="" ; 
  

  CurrentClassroomID = "" ; 


  constructor(private http : HttpClient){
   // this.getAllCustomer(); 

  }

  register()
  {
  
    let bodyData = {
      "ClassroomCapacity" : this.classroomCapacity,
      "ClassroomName" : this.classroomCapacity,
      "ClassroomLvl" : this.classroomLvl
    };
 
    this.http.post("http://localhost:8090/pi/classrooms/Save",bodyData,{responseType: 'text'}).subscribe((resultData: any)=>
    {
        console.log(resultData);
        alert("Student Registered Successfully");
        //this.getAllClassroom();
 
        this.classroomCapacity = 0;
        this.classroomName = '';
        this.classroomLvl  = '';
    });
  }



}
