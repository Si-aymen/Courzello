import { Component , OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-reclamation',
  templateUrl: './reclamation.component.html',
  styleUrl: './reclamation.component.scss'
})
export class ReclamationComponent implements OnInit{

  reclamationArray: any[] = [];
  reclamationID: String = "";
  subject: String = "";
  chartPieData:any ;
  description: string = "";
  state: string = "";
  category: string = "";
  userImpact: number = 0 ;
  priority: number = 0;
  creationDate: string = "2022-05-28T10:00:00";  // Assuming the creation date is a string, adjust as needed
  expectedResolutionDate: string = "2022-05-28T10:00:00";

  your_authenticated_user_id: string = ""; // Initialize with a default value or retrieve dynamically
  attachmentType: string = 'IMAGE'; // Initialize with a default value or retrieve dynamically
  data: string = ""; // Initialize with a default value or retrieve dynamically



  averageResolutionTime: number=0;
  pendingReclamationsCount: number=0;
  CurrentReclamationID = "";
  chartDoughnutData :any ;
  chartDoughnutData2 : any  ;
  advancedStatistics: any;
  chartBarData: any;






  constructor(private http: HttpClient, private router:Router) {
    this.GetAllReclamation();
    this.getAdvancedStatistics();
    this.generateChartPieData();
    this.generateChartBarData();


  }

  ngOnInit(): void {}


  GetAllReclamation() {
    this.http.get("http://localhost:8090/pi/reclamations/get_all").subscribe((resultData: any) => {
      console.log(resultData);
      this.reclamationArray = resultData;
      this.generateChartDoughnutData();
      this.generateChartDoughnutData2();
      this.generateChartPieData();
      this.generateChartBarData();
    });
  }
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

  updateUser(reclamationID: string) {
    const authenticatedUserId = 'your_authenticated_user_id';

    let bodyData = {
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

    this.http.put(`http://localhost:8090/pi/reclamation/${reclamationID}`, bodyData, { responseType: 'text' }).subscribe(
    (resultData: any) => {
      console.log(resultData);
      alert("reclamation updated Successfully");
      this.GetAllReclamation();
      this.clearFormFields();
    },
      (error) => {
        console.error("Error updating reclamation:", error);
        // Handle error as needed
      }
  );
  }

  clearFormFields() {
    this.subject = '';
    this.description = '';
    this.state = '';
    this.category = '';
    this.userImpact = 0;
    this.priority = 0;
    this.creationDate = '';
    this.expectedResolutionDate = '';
  }

  deleteReclamation(reclamationID: string) {
    this.http.delete(`http://localhost:8090/pi/reclamations/delete/${reclamationID}`)
  .subscribe((resultData: any) => {
      console.log(resultData);
      alert('Reclamation deleted successfully');
      this.GetAllReclamation();
    });
  }

  updatePriorities() {
    this.http.put("http://localhost:8090/pi/reclamations/optimize_priorities", {}).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert('Priorities optimized successfully');
        this.GetAllReclamation();
      }
    );
  }
  generateChartDoughnutData() {
    const nameCounts: { [name: string]: number } = {};
    this.reclamationArray.forEach(reclamation => {
      const name = reclamation.state;
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
    this.reclamationArray.forEach(reclamation => {
      const name = reclamation.category;
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


  getAdvancedStatistics() {
    this.http.get("http://localhost:8090/pi/reclamations/advanced_statistics").subscribe((resultData: any) => {
      console.log(resultData);
      this.advancedStatistics = resultData;
    });
  }

  generateChartPieData() {
    const nameCounts: { [name: string]: number } = {};
    this.reclamationArray.forEach(reclamation => {
      const name = reclamation.subject;
      nameCounts[name] = (nameCounts[name] || 0) + 1;
    });

    this.chartPieData = {
      labels: Object.keys(nameCounts),
      datasets: [{
        data: Object.values(nameCounts),
        backgroundColor: this.generateRandomColors(Object.keys(nameCounts).length),
        hoverBackgroundColor: this.generateRandomColors(Object.keys(nameCounts).length)
      }]
    };
  }

  months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];


  generateChartBarData() {
    const monthlyCounts: number[] = new Array(12).fill(0); // Initialize counts for each month to zero
    this.reclamationArray.forEach(reclamation => {
      const monthIndex = new Date(reclamation.creationDate).getMonth();
      monthlyCounts[monthIndex]++;
    });

    this.chartBarData = {
      labels: this.months,
      datasets: [
        {
          label: 'Number of reclamation per month',
          backgroundColor: '#f87979',
          data: monthlyCounts
        }
      ]
    };
  }

  openUpdatePage(reclamation: any) {
    // Assuming you have a route named '/user-update/:id' where ':id' is the user ID
    this.router.navigate(['rec-update/'+reclamation.id]);
  }
}
