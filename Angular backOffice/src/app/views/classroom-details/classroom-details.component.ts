import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute,Router } from '@angular/router';

@Component({
  selector: 'app-classroom-details',
  templateUrl: './classroom-details.component.html',
  styleUrls: ['./classroom-details.component.scss'] 
})
export class ClassroomDetailsComponent implements OnInit {

  classroom: any; 
  classroomCapacity: number = 0;
  classroomName: string = "";
  classroomLvl: string = "";
  speciality: string = "";
  classroomId: string = "";
  CurrentClassroomID = "";
  chartPieData: any;
  chartDoughnutData: any;
  chartDoughnutData2 : any ; 
  userArray :any ; 
  courseArray : any ;
  chartBarData :any ; 

  constructor(private http: HttpClient, private route: ActivatedRoute,private router: Router) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.classroomId = params['id']; 
      this.classroomName = params['classroomName']; 
      this.GetClassroom(); 
      this.GetAllTeachers();
      this.GetAllcourse();
    });
  }

  GetClassroom() {
    this.http.get("http://localhost:8090/pi/classrooms/GetClassroom/" + this.classroomId).subscribe(
      (resultData: any) => {
        console.log(resultData);
        this.classroom = resultData; 
      },
      error => {
        console.error('Error fetching classroom:', error);
       
      }
    );
  }

  GetAllTeachers() {
    this.http.get("http://localhost:8090/pi/users/classroom/"+ this.classroomId ).subscribe((resultData: any) => {
      console.log(resultData);
      this.userArray = resultData;
      this.generateChartDoughnutData2();

    
    });
  }
  GetAllcourse() {
    this.http.get("http://localhost:8090/pi/courses/classroom/" + this.classroomId).subscribe((resultData: any) => {
      console.log(resultData);
      this.courseArray = resultData;
      this.generateChartDoughnutData();
      this. generateChartBarData() ;

    });
  }

  deleteClassroom() {
    this.http.delete("http://localhost:8090/pi/classrooms/DeleteClassroomById/" + this.classroomId).subscribe(
      () => {
        console.log('Classroom deleted successfully');
        alert("Classroom Deleted");
        this.router.navigate(['/#/Classroom/all']);
        // Optionally, you can perform any necessary actions after successful deletion
      },
      error => {
        console.error('Error deleting classroom:', error);
      }
    );
  }



  
  generateChartDoughnutData() {
    const nameCounts: { [name: string]: number } = {};
    this.courseArray.forEach((course:any) => {
      const name = course.courseDomain;
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
    this.userArray.forEach((user:any) => {
      const name = user.speciality;
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
    this.courseArray.forEach((course:any ) => {
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
