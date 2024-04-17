import { Component,OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-select-course',
  templateUrl: './select-course.component.html',
  styleUrls: ['./select-course.component.css']
})
export class SelectCourseComponent {

  courseArray: any[] = [];
  id: String = "";
  courseName: String = "";
  courseLevel: string = "";
  dateAdded: Date =new Date();
  courseDuration : number = 0 ; 
  coursePrice : number =0; 
  courseDescription : string ="";
  CurrentcourseID = "";

  imgNumber:any;


  avRating:any ; 


  
  starIndices = Array(5).fill(0).map((x, i) => i);



  constructor(private http: HttpClient, private route: ActivatedRoute) {
    this.GetAllcourse();
    this.imgNumber = this.getRandomImageNumber();

  }


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.CurrentcourseID = params['id']; 
      this.GetAllcourse();


    });
  }
  GetAllcourse() {
    this.http.get("http://localhost:8090/pi/courses/retrieve-courses").subscribe((resultData: any) => {
      console.log(resultData);
      this.courseArray = resultData;


    
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

  getRandomImageNumber(): number {
    // Generate a random number between 1 and 9
    return Math.floor(Math.random() * 6) + 1;
  }

  getRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  addToClass(course:any) {
    let bodyData = {
    };

    this.http.put("http://localhost:8090/pi/classrooms/AddCourse/"+ this.CurrentcourseID +"/"+course , bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("course  added to classroom Successfully");
      this.avRating = 0;

      

    });
  }



}
