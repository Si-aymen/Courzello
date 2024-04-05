import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router,ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-affect-teachers',
  templateUrl: './affect-teachers.component.html',
  styleUrl: './affect-teachers.component.scss'
})
export class AffectTeachersComponent implements OnInit{

  userArray: any[] = [];
  classroomId :string= "" ; 



  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.GetAlluser();
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.classroomId = params['id']; 
      this.GetAlluser(); 
    });
  }

  GetAlluser() {
    this.http.get("http://localhost:8090/pi/users/GetAllUsers").subscribe((resultData: any) => {
      console.log(resultData);
      this.userArray = resultData;

    });
  }
  
  AffectTeacher(userId: string) {
    this.http.put("http://localhost:8090/pi/classrooms/AddTeacher/" + userId + "/" + this.classroomId, {}).subscribe((resultData: any) => {
      console.log(resultData);
    });

    
  }
  





}
