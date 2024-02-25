import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrl: './conversation.component.scss'
})
export class ConversationComponent implements OnInit {

  conversationArray: any[] = [];
  id: String = "";
  message: String = "";
  description: string = "";
  theme: string = "";
  CurrentConversationID = "";
  chartDoughnutData :any ;


  constructor(private http: HttpClient) {
    this.GetAllConversation();
  }

  ngOnInit(): void {}
  GetAllConversation() {
    this.http.get("http://localhost:8090/pi/conversations/retrieve-conversations").subscribe((resultData: any) => {
      console.log(resultData);
      this.conversationArray = resultData;
      this.generateChartDoughnutData();

    
    });
  }
  add() {
    let bodyData = {
      "message": this.message,
      "description": this.description,
      "theme": this.theme
    };

    this.http.post("http://localhost:8090/pi/conversations/add-conversation", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("Conversation added Successfully");
      this.GetAllConversation();
      this.message = '';
      this.description = '';
      this.theme = '';
    });
  }


  generateChartDoughnutData() {
    const nameCounts: { [name: string]: number } = {};
    this.conversationArray.forEach(conversation => {
      const name = conversation.theme;
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
