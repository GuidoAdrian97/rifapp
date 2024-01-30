import { Component } from '@angular/core';

@Component({
  selector: 'app-create-rifas',
  templateUrl: './create-rifas.component.html',
  styleUrls: ['./create-rifas.component.scss']
})
export class CreateRifasComponent {


  precioTotalpremio:number=0;
  precioRifaboleto:number=0;
  numBoletos:number=0;
  porcentajeUsuario:number=0.20;
  porcentajeEmpresa:number=0.15;
  costoSorteo:number=0;
  MRCS:number=0; //Mínimo rifas costo sorteo
  MRGE:number=0; //Mínimo rifas ganancia empresa
  MRT:number=0; //Mínimo rifas total
  AUX:number=0;
  btnConsult:boolean=true;
  divInformation:boolean=false;
  btnClear:boolean=false;
  divCreate:boolean=true;
  divUpdated:boolean=false;

  MRT1:number=0;
  boletosVender:number=0;
  gananciaVenta:number=0;
  gananciamenosPP:number=0;
  gananciaPorcentajeEmpresa:number=0;
  gananciaPorcentajeUsuario:number=0;
  gananciaUsuario:number=0;
  totalPorcentajes:number=0;
  gananciaFinalUsuario:number=0;

  


  consultar(){

    this.costoSorteo=(this.precioTotalpremio+(this.precioTotalpremio*this.porcentajeUsuario));
    this.MRCS=Math.round((this.costoSorteo/this.precioRifaboleto));
    this.MRGE=Math.round(((this.MRCS*this.porcentajeEmpresa)+1));
    this.MRT=Math.round((this.MRCS+this.MRGE));
    this.AUX=Math.round(((this.MRT*this.precioRifaboleto)-((this.MRT*this.precioRifaboleto)*this.porcentajeEmpresa)));

    if(this.AUX<this.costoSorteo){
      this.MRT++;
    }
    this.numBoletos=this.MRT;
    this.btnConsult=false;
    this.divInformation=true;
    this.btnClear=true;


  }


  consultar1(){
    //MRT=((PrecioPremio/PrecioxRifa)+1)
    //Mínimo de boletos a vender para obtener ganancia mínima y pagar costo de premio
    this.MRT1=((this.precioTotalpremio/this.precioRifaboleto)+1);
  
    this.CalcularGanancias(this.MRT1);

    this.btnConsult=false;
    this.divInformation=true;
    this.btnClear=true;

  }


  CalcularGanancias(boletosNum:number){
  //Ganancia por boletos vendidos
  this.boletosVender=boletosNum;
    this.gananciaVenta=Math.round((this.boletosVender*this.precioRifaboleto)*100)/100;
    //Ganancia de venta menos precio premio
    this.gananciamenosPP=Math.round((this.gananciaVenta-this.precioTotalpremio)*100)/100;
    //Porcentaje de ganancia empresa
    this.gananciaPorcentajeEmpresa=Math.round((this.gananciamenosPP*this.porcentajeEmpresa)*100)/100;
    //Porcentaje de ganancia usuario (saldo )
    this.gananciaPorcentajeUsuario=Math.round((this.gananciamenosPP*this.porcentajeUsuario)*100)/100;
    //total de procentajes de ganancias
    this.totalPorcentajes=Math.round((this.gananciaPorcentajeEmpresa+this.gananciaPorcentajeUsuario)*100)/100;
    //Ganancia usuario (saldo de retiro)
    this.gananciaUsuario=Math.round((this.gananciamenosPP-this.totalPorcentajes)*100)/100;
    //Ganancia final de usuario
    this.gananciaFinalUsuario=Math.round((this.gananciaUsuario+this.gananciaPorcentajeUsuario)*100)/100;
  }


  LimpiarDatos(){
    this.precioTotalpremio=0;
    this.precioRifaboleto=0;
    this.btnConsult=true;
    this.divInformation=false;
    this.btnClear=false;
  }


  CrearRifa(){
    this.divCreate=false;
    this.divUpdated=true;

  }





}
