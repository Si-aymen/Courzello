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


  constructor(private http: HttpClient) {
    this.GetAllConversation();
  }

  ngOnInit(): void {}
  GetAllConversation() {
    this.http.get("http://localhost:8090/pi/conversations/retrieve-conversations").subscribe((resultData: any) => {
      console.log(resultData);
      this.conversationArray = resultData;
    
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


}
