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
  speciality : string="";  
  classroomId : string=""; 
  

  CurrentClassroomID = ""; 


  constructor(private http : HttpClient){
    this.GetAllClassoom(); 

  }

  register()
  {
  
    let bodyData = {

      "classroomCapacity" : this.classroomCapacity,
      "classroomName" : this.classroomName,
      "classroomLvl" : this.classroomLvl
      
    };
 
    this.http.post("http://localhost:8090/pi/classrooms/Save",bodyData,{responseType: 'text'}).subscribe((resultData: any)=>
    {
        console.log(resultData);
        alert("Classroom Registered Successfully");
        this.GetAllClassoom();
        this.classroomCapacity = 0;
        this.classroomName = '';
        this.classroomLvl  = '';
    });
  }

  GetAllClassoom()
  {
    
    this.http.get("http://localhost:8090/pi/classrooms")
  
    .subscribe((resultData: any)=>
    {
    
        console.log(resultData);
        this.classroomArray = resultData;
    });
  }



  setDelete(data: any)
  {
    
    
    this.http.delete("http://localhost:8090/pi/classrooms/DeleteClassroom/"+ "/"+ data._,{responseType: 'text'}).subscribe((resultData: any)=>
    {
        console.log(resultData);
        alert("Student Deletedddd")
        this.GetAllClassoom();
 
        this.classroomName = '';
        this.classroomLvl = '';
        this.classroomCapacity  = 0;
        this.classroomId = ''; 
  
    });
  }







}
