import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Chat } from '../models/chat';
import { ChatService } from '../services/chat.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  public alluser: any = [];
  check = sessionStorage.getItem('userName');
  chatId: any = 0;
  chatObj: Chat = new Chat();
  public chatData: any = [];

  constructor(private router: Router, private userService: UserService, private chatService: ChatService) { }

  ngOnInit(): void {
    let all = setInterval(() => {

      this.userService.getAll().subscribe((data) => {
        // console.log(data);
        this.alluser = data;
      })
    }, 1000);
  }


  goToChat(userName: any) {
    this.chatService.getChatByFirstuserNameAndSeconduserName(userName, sessionStorage.getItem("userName")).subscribe(
      (data) => {
        this.chatId = data.chatId;
        sessionStorage.setItem("chatId", this.chatId);

        sessionStorage.setItem("gotochat", "false");
        this.router.navigateByUrl('/chat');
      },
      (error) => {
        if (error.status == 404) {
          this.chatObj.firstuserName = sessionStorage.getItem("userName");
          this.chatObj.seconduserName = userName;
          this.chatService.createChatRoom(this.chatObj).subscribe(
            (data) => {
              this.chatData = data;
              this.chatId = this.chatData.chatId;
              sessionStorage.setItem("chatId", this.chatData.chatId);

              sessionStorage.setItem("gotochat", "false");
              this.router.navigateByUrl('/chat');
            })
        } else {

        }
      });

  }

}
