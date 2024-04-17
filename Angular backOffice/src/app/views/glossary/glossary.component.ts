import { Component , OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-glossary',
  templateUrl: './glossary.component.html',
  styleUrl: './glossary.component.scss'
})
export class GlossaryComponent implements OnInit{
  glossaryArray: any[] = [];
  filteredGlossaryArray: any[] = [];

  glossaryId: String = "";
  term: string = "";
  definition: string = "";
  editingTermId: string | null = null;



  constructor(private http: HttpClient) {
    this.GetAllTermes();
    //this.getAdvancedStatistics();
    //this.generateChartPieData();
    //this.generateChartBarData();


  }

  ngOnInit(): void {}

  GetAllTermes(){
    this.http.get("http://localhost:8090/pi/glossary/get_all")
      .subscribe((resultData: any) => {
        this.glossaryArray = resultData;
        this.filteredGlossaryArray = [...this.glossaryArray];
      });
  }


  search(): void {
    if (!this.term) {
      this.filteredGlossaryArray = [...this.glossaryArray];
      return;
    }

    this.filteredGlossaryArray = this.glossaryArray.filter(glossary =>
      glossary.term.toLowerCase().includes(this.term.toLowerCase())
    );
  }

  resetSearch(): void {
    this.term = '';
    this.filteredGlossaryArray = [...this.glossaryArray];
  }


  resetReclamationForm() {
    this.term = '';
    this.definition = '';
   
  }

  addGlossary() {
    let bodyData = {
      "term": this.term,
      "definition": this.definition,
    };

    this.http.post("http://localhost:8090/pi/glossary/add", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      alert("course added Successfully");
      this.GetAllTermes();
      this.term = '';
      this.definition = '';
      

    });
}


deleteGlossary(glossaryId: string) {
  this.http.delete(`http://localhost:8090/pi/glossary/delete/${glossaryId}`)
    .subscribe((resultData: any) => {
      console.log(resultData);
      alert('Reclamation deleted successfully');
      this.GetAllTermes();
    });
}


editTerm(termId: string): void {
  this.editingTermId = termId;
  // Pré-remplissez les champs avec les valeurs actuelles du terme sélectionné
  const term = this.glossaryArray.find(term => term.id === termId);
  if (term) {
    this.term = term.term;
    this.definition = term.definition;
  }
}

saveChanges(): void {
  if (!this.editingTermId) {
    return;
  }

  const updatedTerm = {
    term: this.term,
    definition: this.definition
  };

  this.http.patch(`http://localhost:8090/pi/glossary/update/${this.editingTermId}`, updatedTerm)
    .subscribe(() => {
      this.GetAllTermes();
      this.clearForm();
    });
}

clearForm(): void {
  this.editingTermId = null;
  this.term = '';
  this.definition = '';
}


toggleFavorite(termId: string): void {
  const term = this.glossaryArray.find(term => term.glossaryId === termId);
  if (term) {
    term.favorite = !term.favorite; // Toggle favorite state
  }
}


getFavoriteTerms(): any[] {
  return this.glossaryArray.filter(term => term.favorite);
}


}