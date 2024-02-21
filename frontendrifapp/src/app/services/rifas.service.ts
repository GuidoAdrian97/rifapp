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

  guardarRifa(datos:any):Observable<any>{

    const body = new FormData();
    body.append('title', 'rifa de balon');
    body.append('description', 'balon del mundial');
    body.append('cantidad_boletos', '100');
    body.append('rango_inicial_boletos', '1');
    body.append('rango_final_boletos', '100');
    body.append('costo_boleto', '1.00');
    body.append('fecha_sorteo_rifa', '2024-02-29');
    body.append('metodo_sorteo_id', '1');
    
    

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('access_token')}`,
      'Content-Type': 'application/json',
    });
debugger
    return this.httpClient.post<any>(this.api + 'createraffle', body, { headers: headers });
  }

}
