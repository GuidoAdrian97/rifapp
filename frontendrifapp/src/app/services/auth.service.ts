import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  api:string=""
  constructor(private httpClient:HttpClient) { 
    this.api = 'http://rifapp.com:8000/api/';
  }


  authUser(datos:any): Observable<any> {
    const body = new HttpParams()
      .set('name', datos.name)
      .set('identificacion', datos.identificacion)
      .set('telefono',datos.telefono)
      .set('fecha_nacimiento',datos.fecha_nacimiento)
      .set('email',datos.email)
      .set('password',datos.password);
    const url = this.api + 'register';
    return this.httpClient.post<any>(url, body);
  }

  validarNombre(cedula:string): Observable<any>{
    const url = this.api + 'validarcedula';
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = { cedula: cedula };
    return this.httpClient.post<any>(url,body,{ headers })
  }

  validarDatos(identificacion:any,telefono:any,email:any):Observable<any>{
    const body = new FormData();
    body.append('identificacion', identificacion);
    body.append('telefono', telefono);
    body.append('email', email);
    
    const url = this.api + 'verificar_user_data';
    return this.httpClient.post<any>(url,body);
  } 



}