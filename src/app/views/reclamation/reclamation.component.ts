import { Component , OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-reclamation',
  templateUrl: './reclamation.component.html',
  styleUrl: './reclamation.component.scss'
})
export class ReclamationComponent implements OnInit{
  reclamationArray: any[] = [];
  reclamationID: String = "";
  subject: String = "";
  description: string = "";
  state: string = "";
  category: string = "";
  userImpact: number = 0 ;
  priority: number = 0;



  CurrentReclamationID = "";
  chartDoughnutData :any ;

  constructor(private http: HttpClient) {
    this.GetAllReclamation();
  }

  ngOnInit(): void {}
  GetAllReclamation() {
    this.http.get("http://localhost:8090/pi/reclamations/get_all").subscribe((resultData: any) => {
      console.log(resultData);
      this.reclamationArray = resultData;
      this.generateChartDoughnutData();
    });
  }

  
  add() {
    let bodyData = {
      "subject": this.subject,
      "description": this.description,
      "state": this.state,
      "category": this.category,
      "userImpact": this.userImpact,
      "priority": this.priority
    };

    this.http.post("http://localhost:8090/pi/reclamations/post", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("reclamation added Successfully");
      this.GetAllReclamation();
      this.subject = '';
      this.description = '';
      this.state = '';
      this.category = '';
      this.userImpact = 0;
      this.priority = 0;


    });
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

  generateRandomColors(numColors: number): string[] {
    const colors: string[] = [];
    for (let i = 0; i < numColors; i++) {
      colors.push('#' + Math.floor(Math.random() * 16777215).toString(16));
    }
    return colors;
  }


}
