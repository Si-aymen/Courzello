import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-classroom-details',
  templateUrl: './classroom-details.component.html',
  styleUrls: ['./classroom-details.component.scss'] // Use 'styleUrls' instead of 'styleUrl'
})
export class ClassroomDetailsComponent implements OnInit {

  classroom: any; // Rename classroomArray to classroom
  classroomCapacity: number = 0;
  classroomName: string = ""; // Use lowercase 'string' instead of 'String'
  classroomLvl: string = ""; // Use lowercase 'string' instead of 'String'
  speciality: string = "";
  classroomId: string = "";
  CurrentClassroomID = "";
  chartPieData: any;
  chartDoughnutData: any;

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.classroomId = params['id']; 
      this.GetClassroom(); // Call GetClassroom() inside ngOnInit to ensure it's called after classroomId is set
    });
  }

  GetClassroom() {
    this.http.get("http://localhost:8090/pi/classrooms/GetClassroom/" + this.classroomId).subscribe(
      (resultData: any) => {
        console.log(resultData);
        this.classroom = resultData; // Assign resultData directly to classroom
      },
      error => {
        console.error('Error fetching classroom:', error);
        // Handle error here
      }
    );
  }
}
