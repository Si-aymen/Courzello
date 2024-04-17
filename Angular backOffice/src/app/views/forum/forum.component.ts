import { Component,OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppModule } from 'src/app/app.module';
import { Question } from 'src/app/model/question';
import { QuestionServiceService } from 'src/app/service/question-service.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';


@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrl: './forum.component.scss'
})
export class ForumComponent implements OnInit {


  QuestionArray: any[] = [];
  id: String = "";
  title: String = "";
  contenue: string = "";
  tech: string = "";

  CurrentforumID = "";
  
  constructor(private http: HttpClient,private ps: QuestionServiceService,private toastrService: ToastrService,private route: Router) {
    this.GetAllForums();
  }

  ngOnInit(): void {}

  GetAllForums() {
    this.http.get("http://localhost:8090/pi/question/get-questions").subscribe((resultData: any) => {
      console.log(resultData);
      this.QuestionArray = resultData;
    });
  }
  supprimer(item:Question){
    this.ps.deletePost(item.id).subscribe(
      data=>{
        this.toastrService.success("Question supprimé avec succés");
        this.GetAllForums()
        this.route.navigate(['forum'])
      }
      
    )
  }
}
