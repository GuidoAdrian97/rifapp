import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class RifasService {
  api:string="";
  constructor(private httpClient:HttpClient) { 
    this.api = environment.apiBaseDatosUrl
  }

  createSorte():Observable<any>{
    const body = new HttpParams();
    const url = this.api + 'register';
     return this.httpClient.post<any>(url,body);
  }

  tipoSorteo():Observable<any>{ 
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('access_token')}`,
      'Content-Type': 'application/json',
    });

    return this.httpClient.get<any>(this.api + 'listarmetodosorteo', { headers: headers });
  }

/*import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  constructor(private http: HttpClient) { }

  uploadFile(file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('archivo', file, file.name);

    return this.http.post<any>('http://tu-api.com/upload', formData);
  }
} */

  guardarRifa(datos:any):Observable<any>{
    debugger
    const body = JSON.stringify(datos);

    // const body = new FormData();

  // body.append('title', datos.title);
  //   body.append('description', datos.description);
  //   body.append('cantidad_boletos', datos.cantidad_boletos);
  //   body.append('rango_inicial_boletos', datos.rango_inicial_boletos);
  //   body.append('rango_final_boletos', datos.rango_final_boletos);
  //   body.append('costo_boleto', datos.costo_boleto);
  //   body.append('fecha_sorteo_rifa', datos.fecha_sorteo_rifa);
  //   body.append('metodo_sorteo_id', datos.metodo_sorteo_id);
  //   body.append('premios', datos.premios);


    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('access_token')}`,
      'Content-Type': 'application/json',
    });
    
    return this.httpClient.post<any>(this.api + 'createraffle', body, { headers: headers });
  }

}
