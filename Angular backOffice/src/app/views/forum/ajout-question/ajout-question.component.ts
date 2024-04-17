import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Technologie } from 'src/app/model/technologie';
import { QuestionServiceService } from 'src/app/service/question-service.service';

@Component({
  selector: 'app-ajout-question',
  templateUrl: './ajout-question.component.html',
  styleUrl: './ajout-question.component.scss'
})
export class AjoutQuestionComponent {
  questform!: FormGroup;
  tech = Technologie
  currentUser = "65e8b6983e4d9959c9622865";
  constructor(private ps: QuestionServiceService, private formBuilder: FormBuilder, private route: Router
    , private toastrService: ToastrService) {
    ;
  }
  ngOnInit(): void {
    this.initFormquest();

  }
  initFormquest() {
    this.questform = this.formBuilder.group({
      contenue: ['', Validators.required],
      tech: [[], Validators.required],

    });

    this.questform.valueChanges.subscribe(
      data => { console.log(this.questform.value) }
    )
  }
  addquestion() {
    this.ps.ajoutQuestion(this.questform.value, this.currentUser).subscribe(
      res => {
        console.log("av")
        this.toastrService.success("Question ajouté avec succés")
        console.log("ap")
        console.log(res)
        this.route.navigate(['forum'])
      }
    )
  }
}
