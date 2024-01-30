import { Component, OnInit, TemplateRef } from '@angular/core';
import { Router } from '@angular/router';
import { BsModalRef, BsModalService, ModalModule } from 'ngx-bootstrap/modal';
interface RecargaSaldo {
  id:number;
  entidad:string;
  nombre: string;
  numero: number;
  tipoCuenta:string;
  tipoRecarga:string;
  indicaciones:string;
}
@Component({
  selector: 'app-opc-consignacion',
  templateUrl: './opc-consignacion.component.html',
  styleUrls: ['./opc-consignacion.component.scss'],
})
export class OpcConsignacionComponent implements OnInit{

  editar:boolean = true;

  constructor(private modalService: BsModalService){

  }

  ngOnInit(): void {
    
  }

  data: RecargaSaldo[] = [
    { id:1,entidad:'Banco Pichincha',nombre: 'Juan', numero: 25 ,tipoCuenta:'Corriente',tipoRecarga:'Transferencia',indicaciones:' Acercate a cualquier punto Banco Pichincha e indica que haras una transferencia. '+
    'Da los datos correspondientes al tipo de cuenta, número y valor de consignación.'+
   'Una vez realizado el pago por favor sube una foto del comprobante a través del botón reportar consignación.'+
   'Tu consignación será procesada en horario laboral de lunes a domingo de 9:00 am a 9:00 pm, consignación mínima de 2 USD.'},
    { id:2,entidad:'Banco Guayaquil',nombre: 'Maria', numero: 45 ,tipoCuenta:'Corriente',tipoRecarga:'Transferencia',indicaciones:'prueba1'},
    { id:3,entidad:'Banco BanEcuador',nombre: 'Adam', numero: 611 ,tipoCuenta:'Ahorro',tipoRecarga:'Transferencia',indicaciones:'prueba2'},

    // Puedes agregar más datos según sea necesario
  ];
  modalRef?: BsModalRef | null;
  openModalListBanc(template: TemplateRef<void>) {
    this.modalRef = this.modalService.show(template, { id: 1, class: 'modal-lg' });
  }

}
