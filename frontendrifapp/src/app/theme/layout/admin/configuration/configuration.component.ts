// Angular Import
import { Component, inject, TemplateRef, ViewEncapsulation } from '@angular/core';


import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
 
interface RecargaSaldo {
  id:number;
  entidad:string;
  nombre: string;
  numero: number;
  tipoCuenta:string;
  tipoRecarga:string;
  indicaciones:string;
  logo:string;
}

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent {

  data: RecargaSaldo[] = [
    { id:1,entidad:'Banco Pichincha',nombre: 'Juan', numero: 25 ,tipoCuenta:'Corriente',tipoRecarga:'Transferencia',indicaciones:' Acercate a cualquier punto Banco Pichincha e indica que haras una transferencia. '+
    'Da los datos correspondientes al tipo de cuenta, número y valor de consignación.'+
   'Una vez realizado el pago por favor sube una foto del comprobante a través del botón reportar consignación.'+
   'Tu consignación será procesada en horario laboral de lunes a domingo de 9:00 am a 9:00 pm, consignación mínima de 2 USD.',logo:'assets/images/gallery-grid/logobp2.png'},
    { id:2,entidad:'Banco Guayaquil',nombre: 'Maria', numero: 45 ,tipoCuenta:'Corriente',tipoRecarga:'Transferencia',indicaciones:'prueba1',logo:'assets/images/gallery-grid/logobg.png'},
    { id:3,entidad:'Banco BanEcuador',nombre: 'Adam', numero: 611 ,tipoCuenta:'Ahorro',tipoRecarga:'Transferencia',indicaciones:'prueba2',logo:'assets/images/gallery-grid/logobe.png'},

    // Puedes agregar más datos según sea necesario
  ];


 modalRef?: BsModalRef | null;
 modalRef2?: BsModalRef;




  constructor(private modalService: BsModalService) {}
  mostrarForm1: boolean = false;
  mostrarForm2: boolean = false;
  mostrarForm3: boolean = false;
  opciones:boolean=true;
  tipoConsignacion:string='';
  Idcuenta:number=0;
 
  titulo:string="Opciones para recargar saldo.";

  openModal(template: TemplateRef<void>) {
    this.modalRef = this.modalService.show(template, { id: 1, class: 'modal-lg' });
  }


  openModal2(template: TemplateRef<void>,tipo:string,numb:number) {
    this.modalRef2 = this.modalService.show(template, { id: 2, class: 'gray modal-lg' });
    this.tipoConsignacion=tipo;
    this.Idcuenta=numb;
    //document.getElementById('Iduser').value = IDusuario;
    
  }


  closeFirstModal() {
    if (!this.modalRef) {
      return;
    }
 
    this.modalRef.hide();
    this.modalRef = null;
  }
  closeModal(modalId?: number) {
    this.modalService.hide(modalId);
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

  CerrarForm(){
    this.mostrarForm1 = false;
    this.mostrarForm2 = false;
    this.mostrarForm3 = false;
  }


}
