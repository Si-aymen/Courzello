import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Chat } from '../models/chat';
import { User } from '../models/user';
import { ChatService } from '../services/chat.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  chatId: any = 0;
  registerForm: FormGroup;
  loginForm: FormGroup;
  successregister: boolean = false;
  registermsg = "";
  alert = "";
  loginmsg = "";
  alert2 = "";
  successlogin: boolean = false;
  public userObj: User = new User();
  public alluser: any = [];
  seconduserName = "";
  chatObj: Chat = new Chat();
  public chatData: any = [];
  check = "";
  loggedIn: boolean = false;
  loggedOut: boolean = true;
  chatbox: boolean = true;

  logout() {
    this.loggedIn = false;
    this.loggedOut = true;
    sessionStorage.clear();
    
    this.router.navigateByUrl('');
  }

  constructor(private router: Router, private userService: UserService, private chatService: ChatService) {
    this.registerForm = new FormGroup({
      userName: new FormControl("", [Validators.required])
    });
    this.loginForm = new FormGroup({
      userName: new FormControl("", [Validators.required])
    });
  }

  ngOnInit(): void {

    setInterval(() => {
      this.userService.getAll().subscribe((data) => {
        // console.log(data);

        this.alluser = data;

      })
    }, 1000);

  }

  adduser() {
    if (this.registerForm.valid) {
      this.userObj.userName = this.registerForm.value.userName;
      this.userService.adduser(this.userObj).subscribe(
        (data: any) => {
          console.log(data);

          this.successregister = true;
          this.alert = "success";
          this.registermsg = "Successsfully added";

          this.registerForm.reset();

        },
        (error) => {
          console.log(error.error);
          if (error.status == 409) {
            this.successregister = true;
            this.alert = "danger";
            this.registermsg = "Already registered"
          } else {
            this.successregister = true;
            this.alert = "danger";
            this.registermsg = "Error"
          }

        }
      )
    }

  }

  login() {
    if (this.loginForm.valid) {
      this.userService.getUserByuserName(this.loginForm.value.userName).subscribe(
        (data: any) => {
          console.log(data);

          this.successlogin = true;
          this.alert2 = "success";
          this.loginmsg = "Successsfully LoggedIn";

          sessionStorage.setItem("userName", this.loginForm.value.userName);
          this.check = this.loginForm.value.userName;
          this.loginForm.reset();

          this.loggedIn = true;
          this.loggedOut = false;

          // this.router.navigateByUrl('/chat');
        },
        (error) => {
          console.log(error.error);
          if (error.status == 404) {
            this.successlogin = true;
            this.alert2 = "danger";
            this.loginmsg = "Not a registerd user"
          } else {
            this.successlogin = true;
            this.alert2 = "danger";
            this.loginmsg = "Error"
          }

        }
      )
    }


  }

  goToChat(userName: any) {
    this.chatService.getChatByFirstuserNameAndSeconduserName(userName, sessionStorage.getItem("userName")).subscribe(
      (data) => {
        this.chatData = data;
        this.chatId = this.chatData[0].chatId;
        sessionStorage.setItem("chatId", this.chatId);
        this.router.navigateByUrl('/chat');
      },
      (error) => {
        if (error.status == 404) {
          this.chatObj.firstuserName = sessionStorage.getItem("userName");
          this.chatObj.seconduserName = userName;
          this.chatService.createChatRoom(this.chatObj).subscribe(
            (data) => {
              this.chatData = data;
              sessionStorage.setItem("chatId", this.chatData[0].chatId);
              // this.router.navigateByUrl('/chat');
              console.log("2")
            })
        } else {
          // this.router.navigateByUrl('/chat');
          console.log("3")
        }
      });

  }

}
