import { Component, OnInit, ChangeDetectorRef, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-classroom',
  templateUrl: './classroom.component.html',
  styleUrls: ['./classroom.component.scss']
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

  register() {
    let bodyData = {
      "classroomCapacity": this.classroomCapacity,
      "classroomName": this.classroomName,
      "classroomLvl": this.classroomLvl
    };

    this.http.post("http://localhost:8090/pi/classrooms/Save", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("Classroom Registered Successfully");
      this.GetAllClassroom();
      this.classroomCapacity = 0;
      this.classroomName = '';
      this.classroomLvl = '';
    });
  }

  GetAllClassroom() {
    this.http.get("http://localhost:8090/pi/classrooms").subscribe((resultData: any) => {
      console.log(resultData);
      this.classroomArray = resultData;
      this.generateChartPieData();
      this.generateChartDoughnutData();
    });
  }

  setDelete(data: any) {
    this.http.delete("http://localhost:8090/pi/classrooms/DeleteClassroom/" + data._, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("Classroom Deleted");
      this.GetAllClassroom();
      this.classroomName = '';
      this.classroomLvl = '';
      this.classroomCapacity = 0;
      this.classroomId = '';
    });
  }
 


  generateChartDoughnutData() {
    const nameCounts: { [name: string]: number } = {};
    this.classroomArray.forEach(classroom => {
      const name = classroom.classroomLvl;
      nameCounts[name] = (nameCounts[name] || 0) + 1;
    });

    this.chartDoughnutData = {
      labels: Object.keys(nameCounts),
      datasets: [{
        data: Object.values(nameCounts),
        backgroundColor: this.generateRandomColors(Object.keys(nameCounts).length),
        hoverBackgroundColor: this.generateRandomColors(Object.keys(nameCounts).length)
      }]
    };
  }

  generateChartPieData() {
    const nameCounts: { [name: string]: number } = {};
    this.classroomArray.forEach(classroom => {
      const name = classroom.classroomName;
      nameCounts[name] = (nameCounts[name] || 0) + 1;
    });

    this.chartPieData = {
      labels: Object.keys(nameCounts),
      datasets: [{
        data: Object.values(nameCounts),
        backgroundColor: this.generateRandomColors(Object.keys(nameCounts).length),
        hoverBackgroundColor: this.generateRandomColors(Object.keys(nameCounts).length)
      }]
    };
  }



  generateRandomColors(numColors: number): string[] {
    const colors: string[] = [];
    for (let i = 0; i < numColors; i++) {
      colors.push('#' + Math.floor(Math.random() * 16777215).toString(16));
    }
    return colors;
  }

  details(id: string): void {
    this.router.navigate(['/classroom-details', id]);
  }





}
