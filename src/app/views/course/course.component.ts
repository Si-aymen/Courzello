import { Component,OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppModule } from 'src/app/app.module';


@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrl: './course.component.scss'
})
export class CourseComponent implements OnInit {
  

  courseArray: any[] = [];
  id: String = "";
  courseName: String = "";
  courseLevel: string = "";
  dateAdded: Date =new Date();
  courseDuration : number = 0 ; 
  CurrentcourseID = "";
  chartDoughnutData :any ;
  chartDoughnutData2 :any ;


  constructor(private http: HttpClient) {
    this.GetAllcourse();
  }

  ngOnInit(): void {}

  GetAllcourse() {
    this.http.get("http://localhost:8090/pi/courses/retrieve-courses").subscribe((resultData: any) => {
      console.log(resultData);
      this.courseArray = resultData;
      this.generateChartDoughnutData();
      this.generateChartDoughnutData2();

    
    });
  }
  add() {
    let bodyData = {
      "id" : this.id ,
      "courseName": this.courseName,
      "courseLevel": this.courseLevel,
      "dateAdded": this.dateAdded,
      "courseDuration": this.courseDuration
    };

    this.http.post("http://localhost:8090/pi/courses/add-course", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("course added Successfully");
      this.GetAllcourse();

      this.courseName = '';
      this.courseLevel = '';
      this.courseDuration = 0;
      

    });
  }


  generateChartDoughnutData() {
    const nameCounts: { [name: string]: number } = {};
    this.courseArray.forEach(course => {
      const name = course.courseLevel;
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


  generateChartDoughnutData2() {
    const nameCounts: { [name: string]: number } = {};
    this.courseArray.forEach(course => {
      const name = course.courseDuration;
      nameCounts[name] = (nameCounts[name] || 0) + 1;
    });

    this.chartDoughnutData2 = {
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


}
