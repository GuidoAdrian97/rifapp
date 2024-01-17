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
  mostrarForm1: boolean = false;
  mostrarForm2: boolean = false;
  mostrarForm3: boolean = false;
  opciones:boolean=true;
  titulo:string="Opciones para recargar saldo.";



  openModalWithClass(template: TemplateRef<void>) {
   
    this.modalRef = this.modalService.show(template, Object.assign({}, { class: 'gray modal-lg' }));
  }


  openModalWithClass1(template1: TemplateRef<void>) {
 
    this.modalRef = this.modalService.show(template1, Object.assign({}, { class: 'gray modal-lg' }));
  }

  toggleParrafo1() {
    this.mostrarForm1 = true;
    this.mostrarForm2=false;
    this.mostrarForm3=false;
    
  }

  toggleParrafo2() {
    this.mostrarForm2 = true;
    this.mostrarForm1=false;
    this.mostrarForm3=false;
  }

  toggleParrafo3() {
    this.mostrarForm3 = true;
    this.mostrarForm2=false;
    this.mostrarForm1=false;
  
  }

}
