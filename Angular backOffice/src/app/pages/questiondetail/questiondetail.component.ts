import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, forkJoin } from 'rxjs';
import { FileDB } from 'src/app/model/file-db.model';
import { Question } from 'src/app/model/question';
import { Reponse } from 'src/app/model/reponse';
import { Technologie } from 'src/app/model/technologie';
import { QuestionServiceService } from 'src/app/service/question-service.service';
import { ToastrService } from 'ngx-toastr';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-questiondetail',
  templateUrl: './questiondetail.component.html',
  styleUrl: './questiondetail.component.scss'
})
export class QuestiondetailComponent {
  currentUser = "65e8b6983e4d9959c9622865";
  role="admin";
  isReady: boolean = false;
  questform!: FormGroup;
  tech = Technologie
  listofrep: Reponse[] = [];
  listofcmtpagination:Reponse[]=[];
  repo: Reponse[] = [];
  nbrlike!: Number;
  question!:Question;
  isReadyf:boolean=false;
  initialRating: number = 5
  start = 0;
  end = 4;
constructor(private ps: QuestionServiceService, private formBuilder: FormBuilder, private route: Router,
  private ar:ActivatedRoute,private toastrService: ToastrService){}
  ngOnInit(): void { 
    this.initForm();
this.getQuestion(this.ar.snapshot.params['id']);
this.initquestform(this.ar.snapshot.params['id']);

    
  }
  paginate(event: PageEvent) {
    let startIndex = event.pageSize * event.pageIndex;
    this.start = startIndex;
    let endIndex = startIndex + event.pageSize;
    this.end = endIndex;
    if (endIndex > this.question?.reponses.length) {
      endIndex = this.question?.reponses.length;
    }
    this.listofcmtpagination = this.question?.reponses.slice(startIndex, endIndex);
  }
  //////////////////////////Question////////////////////////
getQuestion(id:String){
  this.ps.getQuestionbyId(id).subscribe(
    data=>{
      console.log(data)
      this.question=data;
      this.listofcmtpagination = this.question?.reponses?.slice(this.start, this.end);
      this.isReady=true;
      if (data.reponses != null) {
        for (let reponse of data.reponses) {
          if (reponse != null) {
            console.log(reponse);
            const formGroup = this.initFormmodif(reponse);
            this.formGroups.push(formGroup);
            this.formGroups = data.reponses.map(reponse => this.initFormmodif(reponse));
            this.showParagraph.push(false);
            this.showform=true

          }
        }
      }
      

    }
  )
}
initquestform(data:any){
  this.questform = this.formBuilder.group({
    contenue: [data?.contenue, Validators.required],
    tech: [[], Validators.required],

  });

  this.questform.valueChanges.subscribe(
    data => { console.log(this.questform.value) }
  )
}
addlike(post: Question) {
  this.ps.vote(post.id, this.currentUser, post).subscribe(
    data => {
      this.getQuestion(this.ar.snapshot.params['id']);
      console.log(data)
      this.ps.getnbrvote(post.id).subscribe(
        res => {
          this.getQuestion(this.ar.snapshot.params['id']);
          console.log(this.nbrlike)
          console.log(post)
          this.nbrlike = res;
        }
      )
    }
  )
}
supprimer(item:Question){
  this.ps.deletePost(item.id).subscribe(
    data=>{
      this.toastrService.success("Question supprimé avec succés")
    }
    
  )
}
showmodifquest:boolean=false;
toggleContentquest() {
  this.showmodifquest = !this.showmodifquest;
  this.isReadyf=true;
  this.initquestform(this.question);
}
modifier(item:Question){
  this.ps.updateQuestion(item.id,this.questform.value).subscribe(
    data=>{
      this.toastrService.success("Question modifier avec succés")
      this.getQuestion(item.id);
      this.showmodifquest=false;
    }
  )
}
//////////////////////////:reponse////////////::
selectedFiles!: FileList ;
fileInfos!: Observable<any>;
file!: FileDB;
selectFile(event:any) {
  this.selectedFiles = event.target.files;
}
// Assuming these variables are declared in your component class
files: any[] = []; // Change 'any' to the appropriate type if you have a specific type for files
filesid: string[] = [];

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
          this.toastrService.success("fichier  ajouté avec succés")
          console.log("Uploaded file details:", fileDetails);
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

supprimerfile(idr:String,file:FileDB){
  this.ps.deletefile(file.id).subscribe(
    data=>{
      this.toastrService.success("fichier supprimé avec succés")
        this.getQuestion(this.ar.snapshot.params['id']);
    }
  )
}
/////////////////////////////////////file////////
formGroups: FormGroup[] = [];
showParagraph: boolean[] = [];
repform!: FormGroup;
showform:boolean=false;
toggleContent(index:number) {
  console.log(index)
  console.log(this.showParagraph[index])
  this.showParagraph[index] = !this.showParagraph[index];
  this.getQuestion(this.ar.snapshot.params['id']);

}
initForm() {
  this.repform = this.formBuilder.group({
    contenue: ['', Validators.required]
  });
}
initialiseformgrou(){
  this.repform = this.formBuilder.group({
    contenue: [null],
  });
}
initFormmodif(response: Reponse): FormGroup {
  return this.formBuilder.group({
    contenue: [response?.contenue, Validators.required]
  });
}
ajouterreponse(post: String) {
  console.log(this.repform.value);
  this.ps.addreponse(post, this.currentUser, this.repform.value).subscribe(
    data => {
      this.initialiseformgrou();
      this.getQuestion(this.ar.snapshot.params['id']);

      // Check if filesid is not null before proceeding
      if (this.filesid.length > 0) {
        console.log(this.filesid)
        this.ps.affecterfileaureponse(data.id, this.filesid, data).subscribe(
          res => {
            this.getQuestion(this.ar.snapshot.params['id']);
            this.toastrService.success("Reponse ajouté avec succés")
            this.filesid = [];
            this.initialiseformgrou();
          }
        )
      }else{
        this.toastrService.success("Reponse ajouté avec succés")
      }
    }
  );
}

modifierre(rep: Reponse, index: number) {
  const formGroup = this.formGroups[index];
  this.ps.updateReponse(rep.id, formGroup.value).subscribe(
    data => {
      this.getQuestion(this.ar.snapshot.params['id']);
      if(this.filesid!=null){
        console.log(this.filesid)
        this.ps.affecterfileaureponse(data.id,this.filesid,data).subscribe(
          res=>{
            this.getQuestion(this.ar.snapshot.params['id']);
            this.toastrService.success("Réponse modifié avec succés")
            this.filesid=[];

          }
        )
      }
    }
  )
}
delete(cmt: Reponse) {
  this.ps.deletereponse(cmt.id).subscribe(
    res => {
      this.toastrService.success("Réponse supprimé avec succés")
      this.getQuestion(this.ar.snapshot.params['id']);

    }
  )
}


}
