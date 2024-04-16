import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute,Router } from '@angular/router';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css']
})
export class CourseDetailsComponent {

  courseId : number=0 ; 
  course:any ;
  imgNumber:any;



  starIndices = Array(5).fill(0).map((x, i) => i);




  constructor(private http: HttpClient, private route: ActivatedRoute,private router: Router) {
    this.imgNumber = this.getRandomImageNumber();
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.courseId = params['id']; 
      this.GetCourseById(); 

    });
  }

  GetCourseById() {
    this.http.get("http://localhost:8090/pi/courses/GetCourseById/" + this.courseId).subscribe(
      (resultData: any) => {
        console.log(resultData);
        this.course = resultData; 
      },
      error => {
        console.error('Error fetching classroom:', error);
       
      }
    );
  }


  getRandomImageNumber(): number {
    // Generate a random number between 1 and 9
    return Math.floor(Math.random() * 6) + 1;
  }

}
