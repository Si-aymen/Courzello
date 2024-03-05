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


  constructor(private http: HttpClient, private route: ActivatedRoute) {
    this.GetCourseById();
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.CurrentcourseID = params['id']; 
      this.GetCourseById(); 
    });
  }
  GetCourseById() {
    this.http.get("http://localhost:8090/pi/courses/GetCourseById/"+ this.CurrentcourseID ).subscribe((resultData: any) => {
      console.log(resultData);
      this.course = resultData;
      this.generateChartBarData();
    
    });
  }

  months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];


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

}
