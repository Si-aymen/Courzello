import { Component,OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-course-details',

  templateUrl: './course-details.component.html',
  styleUrl: './course-details.component.scss'
})
export class CourseDetailsComponent implements OnInit {

  course: any ;
  id: String = "";
  courseName: String = "";
  courseLevel: string = "";
  dateAdded: Date =new Date();
  courseDuration : number = 0 ; 
  CurrentcourseID :string= "";
  chartDoughnutData :any ;
  chartDoughnutData2 :any ;
  chartBarData: any;
  chapterArray: any ; 


  constructor(private http: HttpClient, private route: ActivatedRoute) {
    this.GetCourseById();
    this.GetChaptersBycourse();
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.CurrentcourseID = params['id']; 
      this.GetCourseById(); 
      this.GetChaptersBycourse();

    });
  }
  GetCourseById() {
    this.http.get("http://localhost:8090/pi/courses/GetCourseById/"+ this.CurrentcourseID ).subscribe((resultData: any) => {
      console.log(resultData);
      this.course = resultData;
     // this.generateChartBarData();
    
    });
  }

  months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];


  GetChaptersBycourse() {
    this.http.get("http://localhost:8090/pi/chapters/Course/"+ this.CurrentcourseID ).subscribe((resultData: any) => {
      console.log(resultData);
      this.chapterArray = resultData;
      this.generateChartDoughnutData();
    
    });
  }

  generateChartDoughnutData() {
    const nameCounts: { [name: string]: number } = {};
    this.chapterArray.forEach((chapter:any) => {
      const name = chapter.duration;
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

  generateRandomColors(numColors: number): string[] {
    const colors: string[] = [];
    for (let i = 0; i < numColors; i++) {
      colors.push('#' + Math.floor(Math.random() * 16777215).toString(16));
    }
    return colors;
  }

  /*
  generateChartBarData() {
    const monthlyCounts: number[] = new Array(12).fill(0); 
    this.course.forEach((course:any) => {
      const monthIndex = new Date(course.dateAdded).getMonth();
      monthlyCounts[monthIndex]++;
    });

    this.chartBarData = {
      labels: this.months,
      datasets: [
        {
          label: 'Number of Courses per month',
          backgroundColor: '#f87979',
          data: monthlyCounts
        }
      ]
    };
  }
  */


}
