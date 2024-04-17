import { Component,OnInit  } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  username: string = '';
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  password: string = '';
  repeatPassword: string = '';

  constructor(private http: HttpClient, private router: Router) {
  }
  ngOnInit(): void {
  }


  registerUser() {
    let bodyData: UserData = {
      login: this.username,
      password: this.password,
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      role: 'STUDENT'
    };

    this.http.post("http://localhost:8090/pi/users/Save/user", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("Account created Successfully");
    });
  }



}
interface UserData {
  login: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;
  role: string;
  speciality?: string; // Make speciality field optional
}