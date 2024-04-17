import { Component, OnInit, ChangeDetectorRef, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-classroom',
  templateUrl: './classroom.component.html',
  styleUrls: ['./classroom.component.css']
})
export class ClassroomComponent implements OnInit {

  classroomArray: any[] = [];
  classroomCapacity: number = 0;
  classroomName: String = "";
  classroomLvl: String = "";
  speciality: string = "";
  classroomId: string = "";
  CurrentClassroomID = "";
  chartPieData: any;
  chartDoughnutData: any;

  



  constructor(private http: HttpClient, private changeDetectorRef: ChangeDetectorRef,private router: Router) {
    this.GetAllClassroom();

  }

  ngOnInit(): void {}

  GetAllClassroom() {
    this.http.get("http://localhost:8090/pi/classrooms").subscribe((resultData: any) => {
      console.log(resultData);
      this.classroomArray = resultData;
    });
  }

  getRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }




}
