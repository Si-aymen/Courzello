import { Component,OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpResponse, HttpEventType } from '@angular/common/http';


@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrl: './course.component.scss'
})
export class CourseComponent implements OnInit {
  

  courseArray: any[] = [];
  courseName: String = "";
  courseLevel: string = "";
  dateAdded: Date =new Date();
  courseDuration : number = 0 ; 
  CurrentcourseID = "";
  chartDoughnutData :any ;
  chartDoughnutData2 :any ;
  chartBarData: any;


  public imagePath : any ;
  imgURL: any;
  public message: string;
  userFile : any ; 

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
      this.generateChartBarData();

    
    });
  }

  add() {
    let bodyData = {
      "courseName": this.courseName,
      "courseLevel": this.courseLevel,
      "dateAdded": this.dateAdded,
      "courseDuration": this.courseDuration,
      "imgURL": this.imgURL, 
      "imagePath" : this.imagePath,
    };

    this.http.post("http://localhost:8090/pi/courses/add-course", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("course added Successfully");
      this.GetAllcourse();
      this.courseName = '';
      this.courseLevel = '';
      this.imagePath = '';
      this.imgURL='', 
      this.courseDuration = 0;
      

    });
  }
  onFileSelected(event: any, courseId: string) {
    const file: File = event.target.files[0];
    if (file) {
      // Do something with the selected file and courseId
      console.log("Selected file:", file);
      console.log("Course ID:", courseId);
    }
  }

  addPDF(courseId: string) {
    // Create form data to include the course ID
    let formData = new FormData();
  
    this.http.put("http://localhost:8090/pi/courses/course-UploadPDF/" + courseId , formData).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("Course added Successfully");
        this.GetAllcourse();
        this.courseName = '';
        this.courseLevel = '';
        this.courseDuration = 0;
      },
      (error) => {
        console.error("Error adding course:", error);
      }
    );
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


  months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];


  generateChartBarData() {
    const monthlyCounts: number[] = new Array(12).fill(0); // Initialize counts for each month to zero
    this.courseArray.forEach(course => {
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


  onSelectFile(event:any ) {
    if (event.target.files.length > 0)
    {
      const file = event.target.files[0];
      this.userFile = file;
     // this.f['profile'].setValue(file);
 
    var mimeType = event.target.files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.message = "Only images are supported.";
      return;
    }
 
    var reader = new FileReader();
    
    this.imagePath = file;
    reader.readAsDataURL(file); 
    reader.onload = (_event) => { 
      this.imgURL = reader.result; 
    }
  }
     
      
    }



}
