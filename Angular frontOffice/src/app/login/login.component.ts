import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router) { }

  login() {
    this.http.post<any>(`http://localhost:8090/pi/users/login/${this.username}/${this.password}`,null).subscribe(
      (response: any) => {
        if (response) {
          this.router.navigate(['/dashboard']);
        } else {
          alert('Invalid username or password');
        }
      },
      (error) => {
        console.error('Error occurred during login:', error);
      }
    );
  }

  ngOnInit(): void {
  }
}
