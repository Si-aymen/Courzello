import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Reponse } from '../../model/reponse';
import { Question } from '../../model/question';
import { QuestionServiceService } from 'src/app/service/question-service.service';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { Vote } from '../../model/vote';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { Technologie } from '../../model/technologie';
import { Observable, forkJoin } from 'rxjs';
import { FileDB } from 'src/app/model/file-db.model';
import { ToastrService } from 'ngx-toastr';
import { OnInit, OnDestroy, ViewChild, ChangeDetectorRef } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
@Component({
  selector: 'app-forum-front',
  templateUrl: './forum-front.component.html',
  styleUrl: './forum-front.component.scss'
})
export class ForumFrontComponent {
  currentUser = "65e8b6983e4d9959c9622865";
  isReady: boolean = false;
  questform!: FormGroup;
  hide: boolean = true;
  repform!: FormGroup;
  listofrep: Reponse[] = [];
  repo: Reponse[] = [];
  listofQuestion: Question[] = [];
  listofQuestionpagination: Question[] = [];
  tech = Technologie
  nbrlike: Number[] = []
  formGroups: FormGroup[] = [];
  contenue!: string;
  questions!: Question[];
  start = 0;
  end = 4;
  constructor(private ps: QuestionServiceService, private formBuilder: FormBuilder, private route: Router
    , private toastrService: ToastrService, private act: ActivatedRoute) {
    ;
  }
  ngOnInit(): void {
    this.initForm();
    this.getallQuestion();
    this.initFormquest();
    this.getc();
  }

  addquestion() {
    this.ps.ajoutQuestion(this.questform.value, this.currentUser).subscribe(
      res => {
        console.log("av")
        this.toastrService.success("Question ajouté avec succés")
        console.log("ap")
        console.log(res)
        this.getallQuestion()
        this.initialiseformgrou()
      }
    )
  }
  initialiseformgrou(){
    this.questform = this.formBuilder.group({
      contenue: [null],
      tech: [null],

    });
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

  paginate(event: PageEvent) {
    let startIndex = event.pageSize * event.pageIndex;
    this.start = startIndex;
    let endIndex = startIndex + event.pageSize;
    this.end = endIndex;
    if (endIndex > this.listofQuestion.length) {
      endIndex = this.listofQuestion.length;
    }
    this.listofQuestionpagination = this.listofQuestion.slice(startIndex, endIndex);
  }
  getallQuestion() {
    this.ps.getQuestions().subscribe(data => {
      console.log(data);

      this.listofQuestion = data;
      this.listofQuestionpagination = this.listofQuestion.slice(this.start, this.end);
      this.isReady = true;
      for (let question of data) {

        const formGroup = this.initFormquestmodif(question);
        this.questformmodif.push(formGroup);
        this.showmodifquest.push(false);
        if (question.reponses != null) {
          for (let reponse of question.reponses) {
            if (reponse != null) {
              console.log(reponse);
              const formGroup = this.initFormmodif(reponse);
              this.formGroups.push(formGroup);
              this.formGroups = question.reponses.map(reponse => this.initFormmodif(reponse));
              this.showParagraph.push(false);

            }
          }
        }

        let index = this.listofQuestion.indexOf(question);
        this.ps.getnbrvote(question.id).subscribe(res => {
          this.nbrlike[index] = res;
        });
      }
    });
  }
  ajouterreponse(post: String) {
    console.log(this.repform.value);
    this.ps.addreponse(post, this.currentUser, this.repform.value).subscribe(
      data => {
        this.ps.affecterfileaureponse(data.id, this.filesid, data).subscribe(
          res => {
            this.toastrService.success("Reponse ajouté avec succés")
            this.getallQuestion();
          }
        )

      }

    );

  }
  questformmodif: FormGroup[] = [];

  initFormquestmodif(data: Question) {
    return this.formBuilder.group({
      contenue: [data?.contenue, Validators.required]
    });
  }
  techControl = new FormControl();

  // Method to set selected technologies based on the item's tech array

  modifier(item: Question, index: number) {
    const formGroup = this.formGroups[index];
    this.ps.updateQuestion(item.id, formGroup.value).subscribe(
      data => {
        this.toastrService.success("Question modifié avec succés")
        this.getallQuestion();
      }
    )
  }
  supprimer(item: Question) {
    this.ps.deletePost(item.id).subscribe(
      data => {
        this.toastrService.success("Question supprimé avec succés")
        this.getallQuestion();
      }
    )
  }
  getcmtbypos(post: any) {
    this.ps.getreponsebyquestion(post).subscribe(
      data => {

      }
    )
  }


  addlike(post: Question) {
    this.ps.vote(post.id, this.currentUser, post).subscribe(
      data => {
        this.getallQuestion;
        console.log(data)
        this.ps.getnbrvote(post.id).subscribe(
          res => {
            this.getallQuestion;
            console.log(this.nbrlike)
            let index = this.listofQuestion.indexOf(post);
            console.log(index)
            this.nbrlike[index] = res;
          }
        )
      }
    )
  }
  // onTechnologySelected(technology: Technologie) {
  //   // Handle the selected technology here
  //   this.questform.patchValue({
  //     tech: technology
  //   });
  // }
  // onTechnologySelected(select: HTMLSelectElement) {
  //   const selectedOptions = Array.from(select.selectedOptions).map(option => option.value);
  //   this.questform.get('tech')?.setValue(selectedOptions);
  // }
  delete(cmt: Reponse) {
    this.ps.deletereponse(cmt.id).subscribe(
      res => {
        this.toastrService.success("Reponse supprimé avec succés")
        this.getallQuestion();
      }
    )
  }

  modifierre(rep: Reponse, index: number) {
    const formGroup = this.formGroups[index];
    this.ps.updateReponse(rep.id, formGroup.value).subscribe(
      data => {
        this.toastrService.success("Reponse modifié avec succés")
        this.getallQuestion();
      }
    )
  }
  initForm() {
    this.repform = this.formBuilder.group({
      contenue: ['', Validators.required]
    });
  }

  initFormmodif(response: Reponse): FormGroup {
    return this.formBuilder.group({
      contenue: [response?.contenue, Validators.required]
    });
  }

  // questionsWithResponses: any[] = [];
  // showResponse: boolean[][] = []; 
  // showParagraphs: boolean[][] = []; // Array to store the visibility state for each comment
  // initializeVisibilityState(responses:Reponse[]) {
  //   this.showParagraphs = responses.map(() => []);
  //   // Initialize the visibility state for each response's form group
  //   responses.forEach((response, responseIndex) => {
  //     Object.keys(response.formGroup.controls).forEach(controlName => {
  //       const controlIndex = parseInt(controlName);
  //       this.showParagraphs[responseIndex][controlIndex] = false;
  //     });
  //   });
  // }

  // toggleContent(responseIndex: number, controlIndex: number) {
  //   this.showParagraphs[responseIndex][controlIndex] = !this.showParagraphs[responseIndex][controlIndex];
  // }
  showParagraph: boolean[] = [];
  toggleContent(index: number) {
    console.log(index)
    console.log(this.showParagraph[index])
    this.showParagraph[index] = !this.showParagraph[index];
  }
  showmodifquest: boolean[] = [];
  toggleContentquest(index: number) {
    console.log(this.showParagraph)
    this.showmodifquest[index] = !this.showmodifquest[index];
  }
  //////////////////////////////file
  selectedFiles!: FileList;
  fileInfos!: Observable<any>;
  file!: FileDB;
  files!: FileDB[];
  filesid!: String[];
  selectFile(event: any) {
    this.selectedFiles = event.target.files;
  }
  upload(): void {
    if (!this.selectedFiles) {
      console.error("No files selected.");
      return;
    }

    const uploadObservables: Observable<any>[] = [];

    for (let i = 0; i < this.selectedFiles.length; i++) {
      const currentFile = this.selectedFiles.item(i);
      if (!currentFile) {
        console.error("File is null or undefined.");
        continue;
      }
      console.log(currentFile);

      uploadObservables.push(this.ps.upload(currentFile));
    }

    forkJoin(uploadObservables).subscribe(
      uploadResponses => {
        const getFileDetailsObservables: Observable<any>[] = [];

        for (const uploadResponse of uploadResponses) {
          getFileDetailsObservables.push(this.ps.getFilesdetail(uploadResponse));
        }

        forkJoin(getFileDetailsObservables).subscribe(
          fileDetails => {
            console.log("Uploaded file details:", fileDetails);
            this.toastrService.success("fichier chargé avec succés")
            for (const fileDetail of fileDetails) {
              this.files.push(fileDetail);
              this.filesid.push(fileDetail.id)
            }

            // Process the uploaded file details as needed
          },
          error => {
            console.error("Error getting file details:", error);
          }
        );
      },
      error => {
        console.error("Error uploading file:", error);
      }
    );
  }
  supprimerfile(idr: String, file: FileDB) {
    this.ps.deletefile(file.id).subscribe(
      data => {
        this.ps.getFilesbyreponse(idr).subscribe(
          res => {
            this.toastrService.success("fichier supprimé avec succés")
            this.files = res;
          }
        )
      }
    )
  }
  getc() {
    this.act.queryParams.subscribe(
      data => {
        console.log(data['filterValue']);
        this.contenue = data['filterValue'];
        if (!this.contenue) { // Check if filterValue is null or undefined
          this.getallQuestion();
        } else {
          this.gequestionbycontenue();
        }
      }
    );
  }

  gequestionbycontenue() {
    this.ps.getQuestionsByContent(this.contenue).subscribe(
      res => {
        this.listofQuestion = res;
        this.listofQuestionpagination = this.listofQuestion;
        console.log('contenue', this.contenue);
        this.questions.forEach((question, index) => {
          this.initFormquestmodif(question);
          this.ps.getnbrvote(question.id).subscribe(res => {
            this.nbrlike[index] = res;
          });
        });
      }
    );
  }
}

