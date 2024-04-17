import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user: any;
  constructor(private http: HttpClient, private route: ActivatedRoute,private router: Router) {}

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id');
    this.getUserData(userId);
  }

  getUserData(userId: string): void {
    const url = `http://localhost:8090/pi/users/id/${userId}`;
    this.http.get(url).subscribe(
      data => {
        this.user = data;
      },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }


  followUser(userId: string): void {
    const url = `http://localhost:8090/pi/users/follow/${userId}`;
    this.http.post(url, {}).subscribe(
      response => {
        console.log(`Successfully followed user with ID: ${userId}`);
        // Optionally, update the UI or notify the user
      },
      error => {
        console.error(`Error following user with ID: ${userId}`, error);
      }
    );
  }




}
