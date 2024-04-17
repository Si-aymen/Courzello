import { Component } from '@angular/core';
import { SignalBadword } from 'src/app/model/signalBadword';
import { QuestionServiceService } from 'src/app/service/question-service.service';

@Component({
  selector: 'app-badword',
  templateUrl: './badword.component.html',
  styleUrl: './badword.component.scss'
})
export class BadwordComponent {
  badword!:SignalBadword[];
  constructor(private ps: QuestionServiceService) {
  }
  ngOnInit(): void {
    this.getbadword()
  }
  getbadword(){
    this.ps.getBadword().subscribe(
      res=>{
        this.badword=res;
      }
    )
  }
}
