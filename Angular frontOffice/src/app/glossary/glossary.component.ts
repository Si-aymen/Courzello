import { Component , OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TranslationService } from '../translation.service';
@Component({
  selector: 'app-glossary',
  templateUrl: './glossary.component.html',
  styleUrls: ['./glossary.component.css']
})
export class GlossaryComponent implements OnInit {
  glossaryArray: any[] = [];
  filteredGlossaryArray: any[] = [];

  glossaryId: string = "";
  term: string = "";
  definition: string = "";
  editingTermId: string | null = null;
  translatedDefinition: string = "";

  constructor(private http: HttpClient, private translationService: TranslationService) {
    this.getAllTerms();
  }

  ngOnInit(): void {}

  getAllTerms(): void {
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

  resetReclamationForm(): void {
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

  translateTerm(termId: string, targetLanguage: string): void {
    const term = this.glossaryArray.find(term => term.glossaryId === termId);
    if (term) {
      this.translationService.translateTerm(term.definition, targetLanguage)
        .subscribe((translatedDefinition: string) => {
          term.translatedDefinition = translatedDefinition;
        });
    }
  }

  translateDefinition(glossary: any) {
    this.translationService.translateTerm(glossary.term, 'fr')
      .subscribe((response: any) => {
        if (response && response[0] && response[0].translations && response[0].translations[0] && response[0].translations[0].text) {
          glossary.translatedDefinition = response[0].translations[0].text;
        }
      }, (error: any) => {
        console.error('Translation error:', error);
      });
  }
  
}
