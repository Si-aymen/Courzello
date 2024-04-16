import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})

export class UserComponent implements OnInit {

  userArray: any[] = [];
  id: string = "";
  login: string = "";
  password: string = "";
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  dateOfBirth: Date = new Date();
  role: string = "";
  speciality: string = "";
  CurrentuserID = "";
  chartDoughnutData: any;
  chartDoughnutData2: any;
  chartBarData: any;

  constructor(private http: HttpClient, private router: Router) {
    this.GetAlluser();
  }

  ngOnInit(): void { }

  GetAlluser() {
    this.http.get("http://localhost:8090/pi/users/GetAllUsers").subscribe((resultData: any) => {
      console.log(resultData);
      this.userArray = resultData;
      this.generateChartDoughnutData();
      this.generateChartDoughnutData2();
      this.generateChartBarData();
    });
  }

  add() {
    let bodyData: UserData = {
      login: this.login,
      password: this.password,
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      role: this.role
    };
    if(this.role!='TEACHER')
    {
      this.speciality='';
    }
    if (this.speciality!='') {
      bodyData["speciality"] = this.speciality;
    }

    this.http.post("http://localhost:8090/pi/users/Save/user", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("User added Successfully");
      this.GetAlluser();
      this.clearFormFields();
    });
  }

  updateUser(login: string) {
    let bodyData = {
      "firstName": this.firstName,
      "lastName": this.lastName,
      "email": this.email,
      "role": this.role,
      "speciality": this.speciality
    };

    this.http.put(`http://localhost:8090/pi/users/${login}`, bodyData, { responseType: 'text' }).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("User updated Successfully");
        this.GetAlluser();
        this.clearFormFields();
      },
      (error) => {
        console.error("Error updating user:", error);
        // Handle error as needed
      }
    );
  }

  deleteUser(login: string) {
    this.http.delete(`http://localhost:8090/pi/users/${login}`).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("User deleted Successfully");
        this.GetAlluser();
      },
      (error) => {
        console.error("Error deleting user:", error);
        // Handle error as needed
      }
    );
  }

  getUserByLogin(login: string) {
    this.http.get(`http://localhost:8090/pi/users/${login}`).subscribe(
      (userData: any) => {
        console.log(userData);
        // Do something with the user data, maybe populate a form for editing
      },
      (error) => {
        console.error("Error fetching user:", error);
        // Handle error as needed
      }
    );
  }

  clearFormFields() {
    this.login = '';
    this.password = '';
    this.firstName = '';
    this.lastName = '';
    this.email = '';
    this.role = '';
    this.speciality = '';
  }

  onFileSelected(event: any, userId: string) {
    const file: File = event.target.files[0];
    if (file) {
      // Do something with the selected file and userId
      console.log("Selected file:", file);
      console.log("user ID:", userId);
    }
  }

  addPDF(userId: string) {
    let formData = new FormData();

    this.http.put(`http://localhost:8090/pi/users/Save/user/${userId}`, formData).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("User added Successfully");
        this.GetAlluser();
        this.clearFormFields();
      },
      (error) => {
        console.error("Error adding user:", error);
        // Handle error as needed
      }
    );
  }

  generateChartDoughnutData() {
    const nameCounts: { [name: string]: number } = {};
    this.userArray.forEach(user => {
      const name = user.speciality;
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

  generateChartDoughnutData2() {
    const nameCounts: { [name: string]: number } = {};
    this.userArray.forEach(user => {
      const name = user.role;
      nameCounts[name] = (nameCounts[name] || 0) + 1;
    });

    this.chartDoughnutData2 = {
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

  months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

  generateChartBarData() {
    const monthlyCounts: number[] = new Array(12).fill(0); // Initialize counts for each month to zero
    this.userArray.forEach(user => {
      const monthIndex = new Date(user.dateAdded).getMonth();
      monthlyCounts[monthIndex]++;
    });

    this.chartBarData = {
      labels: this.months,
      datasets: [
        {
          label: 'Number of users per month',
          backgroundColor: '#f87979',
          data: monthlyCounts
        }
      ]
    };
  }
  openUpdatePage(user: any) {
    // Assuming you have a route named '/user-update/:id' where ':id' is the user ID
    this.router.navigate(['user-update/'+user.id]);
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
