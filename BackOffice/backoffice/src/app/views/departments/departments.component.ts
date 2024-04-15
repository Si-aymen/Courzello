import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-department',
  templateUrl: './departments.component.html',
  styleUrls: ['./departments.component.scss']
})
export class DepartmentsComponent implements OnInit {

  departmentArray: any[] = [];
  departmentId: string = "";
  name: string = "";
  contactInformation: string = "";

  constructor(private http: HttpClient, private router: Router) {
    this.getAllDepartments();
  }

  ngOnInit(): void { }

  getAllDepartments() {
    this.http.get("http://localhost:8090/pi/Departments/GetAllDepartments").subscribe((resultData: any) => {
      console.log(resultData);
      this.departmentArray = resultData;
    });
  }

  addDepartment() {
    let bodyData: DepartmentData = {
      name: this.name,
      contactInformation: this.contactInformation
    };

    this.http.post("http://localhost:8090/pi/Departments/Save/department", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("Department added Successfully");
      this.getAllDepartments();
      this.clearFormFields();
    });
  }

  deleteDepartment(departmentId: string) {
    this.http.delete(`http://localhost:8090/pi/Departments/${departmentId}`).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("Department deleted Successfully");
        this.getAllDepartments();
      },
      (error) => {
        console.error("Error deleting department:", error);
        // Handle error as needed
      }
    );
  }

  clearFormFields() {
    this.name = '';
    this.contactInformation = '';
  }

  openUpdatePage(department: any) {
    // Assuming you have a route named '/user-update/:id' where ':id' is the user ID
    this.router.navigate(['department-update/'+department.departmentId]);
  }





}

interface DepartmentData {
  name: string;
  contactInformation: string;
}
