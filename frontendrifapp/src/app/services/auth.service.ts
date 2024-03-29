import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  api:string=""
  constructor(private httpClient:HttpClient) { 
    this.api = environment.apiBaseDatosUrl;
  }


  authUser(datos:any): Observable<any> {
    const body = new HttpParams()
      .set('name', datos.name)
      .set('identificacion', datos.identificacion)
      .set('telefono',datos.telefono)
      .set('fecha_nacimiento',datos.fecha_nacimiento)
      .set('email',datos.email)
      .set('password',datos.password)
      .set('referrerCode',datos.referrerCode);
    const url = this.api + 'register';
    return this.httpClient.post<any>(url, body);
  }

  validarNombre(cedula:string): Observable<any>{
    const url = this.api + 'validarcedula';
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = { cedula: cedula };
    return this.httpClient.post<any>(url,body,{ headers })
  }

  validarDatos(identificacion:any,telefono:any,email?:any):Observable<any>{
    const body = new FormData();
    if(identificacion == ""){
      identificacion = "."
    }
    if(telefono == ""){
      telefono = "."
    }
    if(email == "" ){
      email = "."
    }
    body.append('identificacion', identificacion);
    body.append('telefono', telefono);
    body.append('email', email);
    
    const url = this.api + 'verificar_user_data';
    return this.httpClient.post<any>(url,body);
  } 

  updateregistersocialite(datos:any):Observable<any>{
    const body = new FormData();
    body.append('accessToken', datos.accessToken);
    body.append('identificacion', datos.identificacion);
    body.append('telefono', datos.telefono);
    body.append('fecha_nacimiento', datos.fecha_nacimiento);
    body.append('password', datos.password);
    body.append('name', datos.name);
    body.append('referrerCode', datos.referrerCode);
    const url = this.api + 'updateregistersocialite';
    return this.httpClient.post<any>(url,body);
  } 

  login(datos:any):Observable<any>{
    const body = new FormData();
    body.append('email', datos.email);
    body.append('password', datos.password);
    const url = this.api + 'login';
    return this.httpClient.post<any>(url,body);
  }

  validarCodigoReferencia(referrerCode:any):Observable<any>{
     // Utiliza el objeto HttpParams para incluir el parámetro
     const params = new HttpParams().set('referrerCode', referrerCode);

     // Realiza la solicitud GET con los parámetros y devuelve el observable
     return this.httpClient.get(`${this.api}ValidateCodeReferral`, { params });
  }

  referidoPrincipal():Observable<any>{
    return this.httpClient.get(`${this.api}ReferidoPrincipal`);
  }
  



}
