import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.scss']
})
export class UserUpdateComponent implements OnInit {
  userId: string='';
  user: any;

  constructor( private route: ActivatedRoute, private http: HttpClient,     private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.userId = id;
        this.http.get<any>('http://localhost:8090/pi/users/get-id/' + this.userId).subscribe((data: any) => {
          this.user = data;
        });
      }
    });
  }
  updateUser() {
    if(this.user.role!="TEACHER")
    {
      this.user.speciality=null;
    }
    this.http.put(`http://localhost:8090/pi/users/${this.user.login}`, this.user).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("User updated Successfully");
        // Redirect back to UserComponent after updating user
        this.router.navigate(['/user']);
      },
      (error) => {
        console.error("Error updating user:", error);
      }
    );
  }
}
