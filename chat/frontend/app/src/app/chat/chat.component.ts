import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Chat } from '../models/chat';
import { Message } from '../models/message';
import { ChatService } from '../services/chat.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  chatForm: FormGroup;
  chatObj: Chat = new Chat();
  messageObj: Message = new Message();
  // chatId: number = 22;
  public messageList: any = [];
  public chatList: any = [];
  replymessage: String = "checking";
  public chatData: any;
  msg = "Good work";
  chatId: any = sessionStorage.getItem('chatId');
  color = "";
  seconduserName = "";
  public alluser: any = [];
  check = sessionStorage.getItem('userName');
  timesRun = 0;
  timesRun2 = 0;


  firstuserName = sessionStorage.getItem('userName');
  senderEmail = sessionStorage.getItem('userName');
  senderCheck = sessionStorage.getItem('userName');

  constructor(private chatService: ChatService, private router: Router, private userService: UserService) {
    this.chatForm = new FormGroup({
      replymessage: new FormControl()
    });

  }

  ngOnInit(): void {
    setInterval(() => {
      this.chatService.getChatById(sessionStorage.getItem('chatId')).subscribe(data => {
        this.chatData = data;
        this.messageList = this.chatData.messageList;
        this.seconduserName = this.chatData.seconduserName;
        this.firstuserName = this.chatData.firstuserName;
      });
    }, 1000);


    let getByname = setInterval(() => {
      // For getting all the chat list whose ever is logged in.
      this.chatService.getChatByFirstuserNameOrSeconduserName(sessionStorage.getItem('userName')).subscribe(data => {
        // console.log(data);
        this.chatData = data;
        this.chatList = this.chatData;
      });

      this.timesRun2 += 1;
      if (this.timesRun2 === 2) {
        clearInterval(getByname);
      }
    }, 1000);

    let all = setInterval(() => {

      this.userService.getAll().subscribe((data) => {
        // console.log(data);

        this.alluser = data;
      })

      this.timesRun += 1;
      if (this.timesRun === 2) {
        clearInterval(all);
      }
    }, 1000);


  }

  loadChatByEmail(event: string, event1: string) {
    console.log(event, event1);
    // For removing the previous chatId
    sessionStorage.removeItem("chatId");

    // For checking the chat room by both the emails , if there is present then it will give the chat Id in sessionStorage
    this.chatService.getChatByFirstuserNameAndSeconduserName(event, event1).subscribe(data => {
      // console.log(data);
      this.chatData = data;
      this.chatId = this.chatData[0].chatId;
      console.log(this.chatId);
      sessionStorage.setItem('chatId', this.chatId)


      setInterval(() => {
        this.chatService.getChatById(this.chatId).subscribe(data => {
          this.chatData = data;
          this.messageList = this.chatData.messageList;
          this.seconduserName = this.chatData.seconduserName;
          this.firstuserName = this.chatData.firstuserName;
        });
      }, 1000)

    });

  }

  sendMessage() {
    console.log(this.chatForm.value);

    // This will call the update chat method when ever user send the message
    this.messageObj.replymessage = this.chatForm.value.replymessage;
    this.messageObj.senderEmail = this.senderEmail;
    this.chatService.updateChat(this.messageObj, this.chatId).subscribe(data => {
      console.log(data);
      this.chatForm.reset();

      // for displaying the messageList by the chatId
      this.chatService.getChatById(this.chatId).subscribe(data => {
        console.log(data);
        this.chatData = data;
        // console.log(this.chatData.messageList);console.log(JSON.stringify(this.chatData.messageList));
        this.messageList = this.chatData.messageList;
        this.seconduserName = this.chatData.seconduserName;
        this.firstuserName = this.chatData.firstuserName;

      })
    })
  }

  routeX() {
    // this.router.navigateByUrl('/navbar/recommendation-service');
    sessionStorage.clear();
    // window.location.reload();
    this.router.navigateByUrl('');
  }

  routeHome() {
    this.router.navigateByUrl('');
  }


  goToChat(userName: any) {
    this.chatService.getChatByFirstuserNameAndSeconduserName(userName, sessionStorage.getItem("userName")).subscribe(
      (data) => {
        this.chatId = data.chatId;
        sessionStorage.setItem("chatId", this.chatId);
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
            })
        } else {

        }
      });

  }
  

  deleteChat(): void {
    if (confirm("Are you sure you want to delete this chat?")) {
      this.chatService.deleteChat(this.chatId).subscribe(
        () => {
          // Handle successful deletion
          console.log('Chat deleted successfully');
          // Redirect to a different page or perform any other action
        },
        error => {
          // Handle error response
          console.error('Error deleting chat:', error);
          // Display an error message or perform any other action
        }
      );
    }
  }

}
