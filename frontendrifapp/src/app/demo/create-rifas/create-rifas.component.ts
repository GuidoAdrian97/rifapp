import { Component, OnInit } from '@angular/core';

import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { relative } from 'path';
import { RifasService } from 'src/app/services/rifas.service';

interface Rifa {
  title:string;
  description:string;
  cantidad_boletos:number;
  rango_inicial_boletos:number;
  rango_final_boletos:number;
  costo_boleto:number;
  fecha_sorteo_rifa:string;
  metodo_sorteo_id:number;
}


@Component({
  selector: 'app-create-rifas',
  templateUrl: './create-rifas.component.html',
  styleUrls: ['./create-rifas.component.scss'],


})
export class CreateRifasComponent implements OnInit {

  typeRifa:any;
  constructor(private serviceRifa: RifasService) {
    this.serviceRifa.tipoSorteo().subscribe({
      next:rest =>{
        this.typeRifa = rest['Metodos de Sorteo'];
        console.log(this.typeRifa)
      },error:error => {
        console.log(error)
      }
    })
  }

  ngOnInit() {

  }

  guardarDatos(){
    let datos:Rifa = {
      'title':this.tituloRifa,
      'rango_inicial_boletos':1,
      'rango_final_boletos':2,
      'metodo_sorteo_id':1,
      'fecha_sorteo_rifa':'dasda',
      'description':'dasdas',
      'costo_boleto':1,
      'cantidad_boletos':2
    }
    this.serviceRifa.guardarRifa(datos).subscribe({
      next:rest=>{
        debugger
      },error:error =>{
        console.log(error)
      }
    })
  }

  precioTotalpremio: number = 0;
  precioRifaboleto: number = 0;
  minBoletos: number = 0;
  numBoletos: number = 0;
  porcentajeUsuario: number = 0.20;
  porcentajeEmpresa: number = 0.15;
  costoSorteo: number = 0;
  MRCS: number = 0; //Mínimo rifas costo sorteo
  MRGE: number = 0; //Mínimo rifas ganancia empresa
  MRT: number = 0; //Mínimo rifas total
  AUX: number = 0;

  btnConsult: boolean = true;
  divInformation: boolean = true;
  divCreate: boolean = false;
  opcPublicar: boolean = false;
  divUpdated: boolean = false;


  boletosVender: number = 0;
  gananciaVenta: number = 0;
  gananciamenosPP: number = 0;
  gananciaPorcentajeEmpresa: number = 0;
  gananciaUsuario: number = 0;
  gananciaFinalUsuario: number = 0;


  opcSorteo: number = 0;
  fechaSorteo: string = "";
  tituloRifa: string = "";
  descripRifa: string = "";
  intervaloBoletos: number = 0;
  premiosCantidad: number = 0;
  porcentajeMinBoletos: number = 10;
  minBoletosxPersona: number = 0;
  AUX2: number = 0;


  opcOtraCategoria: boolean = false;


  consultar() {

    this.costoSorteo = (this.precioTotalpremio + (this.precioTotalpremio * this.porcentajeUsuario));
    this.MRCS = (this.costoSorteo / this.precioRifaboleto);
    this.MRGE = ((this.MRCS * this.porcentajeEmpresa) + 1);
    this.MRT = (this.MRCS + this.MRGE);
    this.AUX = ((this.MRT * this.precioRifaboleto) - ((this.MRT * this.precioRifaboleto) * this.porcentajeEmpresa));

    if (this.AUX < this.costoSorteo) {
      this.MRT++;
    }

    this.minBoletos = this.redondearArribaValor(this.MRT);



    this.CalcularGanancias(this.minBoletos);
    //this.ConsultarMinBoletosxPersona(this.minBoletos);

  }



  ConsultarMinBoletosxPersona(num: number) {
    this.AUX2 = this.porcentajeMinBoletos / 100;
    this.minBoletosxPersona = num * this.AUX2;
    this.minBoletosxPersona = this.redondearArribaValor(this.minBoletosxPersona);
  }



  redondearArribaValor(Num: number) {
    var redondeado = Math.floor(Num);
    if (Num - redondeado >= 0.2) {
      redondeado += 1;
    }
    return redondeado;
  }




  CalcularGanancias(boletosNum: number) {
    //Ganancia por boletos vendidos
    this.boletosVender = boletosNum;
    this.gananciaVenta = Math.round((this.boletosVender * this.precioRifaboleto) * 100) / 100;
    //Ganancia de venta menos precio premio
    this.gananciamenosPP = Math.round((this.gananciaVenta - this.precioTotalpremio) * 100) / 100;
    //Ganancia empresa
    this.gananciaPorcentajeEmpresa = Math.round((this.gananciaVenta * this.porcentajeEmpresa) * 100) / 100;

    //Ganancia usuario (saldo de retiro)
    this.gananciaUsuario = Math.round((this.gananciaVenta - this.gananciaPorcentajeEmpresa) * 100) / 100;
    //Ganancia final de usuario
    this.gananciaFinalUsuario = Math.round((this.gananciaUsuario - this.precioTotalpremio) * 100) / 100;

    //agrege esto
    this.numBoletos = this.boletosVender;

    //llamar funcion de calcular minimo x persona 
    this.ConsultarMinBoletosxPersona(this.numBoletos);

  }


  AgregarCategoria() {
    this.opcOtraCategoria = true;
  }


  CrearRifa() {
    this.divCreate = true;
    this.divInformation = false;
    this.divUpdated = false;
    this.opcPublicar = false;

  }




  VerInfoGanancias() {
    this.divCreate = false;
    this.divInformation = true;
    this.divUpdated = false;
    this.opcPublicar = false;
  }




  VerSeccionPremios() {
    this.divCreate = false;
    this.divInformation = false;
    this.divUpdated = true;
    this.opcPublicar = false;
  }




  PublicarRifa() {
    this.opcPublicar = true;
    this.divCreate = false;
    this.divInformation = false;
    this.divUpdated = false;
  }


  public archivos: NgxFileDropEntry[] = [];
  public archivosAgregados: string[] = [];
  public archivosAgregados2: any[] = [];
  public archivosEnLista: string = '';


  public files: NgxFileDropEntry[] = [];


  public dropped(files: NgxFileDropEntry[]) {
    this.archivos = files;

    for (const droppedFile of this.archivos) {
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {
          this.archivosAgregados.push(file.name);
          this.actualizarLista();
        });
      }
    }


    this.files = files;
    //this.previewImages();
  }

  private actualizarLista() {
    this.archivosEnLista = this.archivosAgregados.join(' - ');

  }


  public fileOver(event: any) {
  }

  public fileLeave(event: any) {
  }






}

