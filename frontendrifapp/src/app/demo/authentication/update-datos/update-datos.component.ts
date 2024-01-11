import { Component,OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-update-datos',

  templateUrl: './update-datos.component.html',
  styleUrls: ['./update-datos.component.scss']
})
export class UpdateDatosComponent implements OnInit {

  constructor(){}

  ngOnInit():void{}
  

  nameUpdated:string=""; 
  identificacionUpdated:string="";
  telefonoUpdated:string=""; 
  fecha_nacimientoUpdated:string="";
  emailUpdated:string="";
  passwordUpdated:string="";

  ActualizarDatos(){
   alert("datos actualizados!!!")
  }



}

