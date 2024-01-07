import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class GoogleserviceService {

  constructor(private http: HttpClient) { }

  googleLogin(): Observable<any>{
    let url = 'http://rifapp.com:8000/google-auth/redirect'
     return this.http.get(url);
   }
}


