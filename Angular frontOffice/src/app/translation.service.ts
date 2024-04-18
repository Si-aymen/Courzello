import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TranslationService {
  private apiKey = 'dfb53300668c4ff28eeeef5660f4bae2';
  private apiUrl = 'https://translation.api.com';

  constructor(private http: HttpClient) { }

  translateTerm(term: string, targetLanguage: string): Observable<any> {
    const url = `${this.apiUrl}/translate?key=${this.apiKey}&target=${targetLanguage}`;
    const body = [{ text: term }];
    return this.http.post<any>(url, body);
  }
  
}
