import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-departments-update',
  templateUrl: './departments-update.component.html',
  styleUrl: './departments-update.component.scss'
})
export class DepartmentsUpdateComponent implements OnInit {
  departmentId: string = '';
  department: any;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.departmentId = id;
        this.http.get<any>('http://localhost:8090/pi/Departments/' + this.departmentId).subscribe((data: any) => {
          this.department = data;
        });
      }
    });
  }

  updateDepartment() {
    this.http.put(`http://localhost:8090/pi/Departments/${this.department.departmentId}`, this.department).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("Department updated Successfully");
        // Redirect back to DepartmentsComponent after updating department
        this.router.navigate(['/departments']);
      },
      (error) => {
        console.error("Error updating department:", error);
      }
    );
  }
}
