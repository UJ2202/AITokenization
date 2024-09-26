import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ControllerServiceService {

  private baseUrl = 'http://localhost:8080'; // Replace with your API URL

  constructor(private http: HttpClient) {}

  getTokenizers(): Observable<any> {
    return this.http.get<string[]>(`${this.baseUrl}/aitokenizer/list/tokenizers`);
  }

  search(payload: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/aitokenizer/validateTokenCount`, payload);
  }
}
