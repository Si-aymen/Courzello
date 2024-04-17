import { Component , OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  reclamationArray: any[] = [];
  reclamationID: String = "";
  subject: String = "";
  chartPieData:any ;
  description: string = "";
  state: string = "EN_ATTENTE";
  category: string = "";
  userImpact: number = 0 ;
  priority: number = 0;
  creationDate: string = "2022-05-28T10:00:00";  // Assuming the creation date is a string, adjust as needed
  expectedResolutionDate: string = "2022-05-28T10:00:00";

  your_authenticated_user_id: string = ""; // Initialize with a default value or retrieve dynamically
  attachmentType: string = 'IMAGE'; // Initialize with a default value or retrieve dynamically
  data: string = ""; // Initia

  constructor(private http: HttpClient) {}

  
  ngOnInit(): void {}

  resetReclamationForm() {
    this.subject = '';
    this.description = '';
    this.state = '';
    this.category = '';
    this.userImpact = 0;
    this.priority = 0;
    this.creationDate = '';
    this.expectedResolutionDate = '';
   // this.averageResolutionTime=0;
    //this.pendingReclamationsCount=0;
  }



  addReclamation() {
    const authenticatedUserId = 'your_authenticated_user_id';

    const bodyData = {
      "rec": {
        "subject": this.subject,
        "description": this.description,
        "state": this.state,
        "category": this.category,
        "userImpact": this.userImpact,
        "priority": this.priority,
        "creationDate": this.creationDate,
        "expectedResolutionDate": this.expectedResolutionDate,
        "user": { "id": authenticatedUserId }
      },
      "att": {
        "type": this.attachmentType,
        "data": this.data
      }
    };

    

    this.http.post("http://localhost:8090/pi/reclamations/post", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("Reclamation added Successfully");
      this.resetReclamationForm();
    });
  }
  navigateToOtherProject(): void {
    // Replace 'other-project-url' with the actual URL of the other project
    window.location.href = 'http://localhost:64699/';
  }
  navigateToOtherProject2(): void {
    // Replace 'other-project-url' with the actual URL of the other project
    window.location.href = 'http://localhost:4200/chat/10/' ;

}
}