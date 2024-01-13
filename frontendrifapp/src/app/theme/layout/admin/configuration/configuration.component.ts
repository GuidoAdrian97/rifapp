// Angular Import
import { Component, inject, TemplateRef, ViewEncapsulation } from '@angular/core';

import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
 
@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent {

 modalRef?: BsModalRef;
  constructor(private modalService: BsModalService) {}
  mostrarForm:boolean=false;
  mostrarForm1: boolean = false;
  mostrarForm2: boolean = false;
  mostrarForm3: boolean = false;
  mostrarFormG:boolean=false;
  opciones:boolean=true;
  titulo:string="";



  openModalWithClass(template: TemplateRef<void>) {
    this.opciones=true;
    this.mostrarFormG=false;
    this.titulo="Opciones para recargar saldo.";
    this.mostrarForm=false;
    this.mostrarForm1=false;
    this.mostrarForm2=false;
    this.mostrarForm3=false;
    this.modalRef = this.modalService.show(template, Object.assign({}, { class: 'gray modal-lg' }));
  }

  
  toggleParrafo1() {
    this.mostrarFormG=true;
    this.mostrarForm1 = true;
    this.mostrarForm = true;
    this.titulo="Recargar saldo por transferencia bancaria.";
    this.opciones=false;
  }

  toggleParrafo2() {
    this.mostrarFormG=true;
    this.mostrarForm2 = true;
    this.mostrarForm=true;
    this.titulo="Recargar saldo por Paypal.";
    this.opciones=false;
  }

  toggleParrafo3() {
    this.mostrarFormG=true;
    this.mostrarForm3 = true;
    this.mostrarForm=true;
    this.titulo="Recargar saldo por tarjetas.";
    this.opciones=false;
  }
 
  Cancelar() {
    this.mostrarFormG=false;
    this.opciones=true;
    this.mostrarForm =false;
    this.mostrarForm1 =false;
    this.mostrarForm2=false;
    this.mostrarForm3=false;
    this.titulo="Opciones para recargar saldo.";
  }

}
