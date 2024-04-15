import { Component,OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppModule } from 'src/app/app.module';


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
  
  constructor(private http: HttpClient) {
    this.GetAllForums();
  }

  ngOnInit(): void {}

  GetAllForums() {
    this.http.get("http://localhost:8090/pi/question/get-questions").subscribe((resultData: any) => {
      console.log(resultData);
      this.QuestionArray = resultData;
    });
  }
}
