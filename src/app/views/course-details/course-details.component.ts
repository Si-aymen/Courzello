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
    
    });
  }

}
