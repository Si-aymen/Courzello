import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.scss'
})
export class UserProfileComponent implements OnInit {
  userData: any;
  ngOnInit(): void {
    this.fetchUserData();
  }
  constructor(private http: HttpClient) {}
  fetchUserData() {
    // Make HTTP GET request to fetch user data
    this.http.get<any>('http://localhost:8090/pi/users/connectedUser').subscribe(
      (data) => {
        this.userData = data; // Assign received user data to userData variable
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
  }


}
