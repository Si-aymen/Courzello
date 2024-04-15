import { Component,OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-rec-update',
  templateUrl: './rec-update.component.html',
  styleUrl: './rec-update.component.scss'
})

export class RecUpdateComponent implements OnInit {
  reclamationID: string='';
  reclamation: any;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {}

  ngOnInit():void{
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.reclamationID = id;
        this.http.get<any>('http://localhost:8090/pi/reclamations/get/' +this.reclamationID).subscribe((data: any) =>{
          this.reclamation= data;
        });
      }

    });
  }

  updateReclamation(){
    this.http.put('http://localhost:8090/pi/reclamations/put/${reclamationID} ', this.reclamation ).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("reclamation updated Successfully");
        // Redirect back to UserComponent after updating user
        this.router.navigate(['/reclamations']);
      },
      (error) => {
        console.error("Error updating user:", error);
        // Handle error as needed
      }
    );
  }
}
