import { Component } from '@angular/core';

import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { relative } from 'path';




@Component({
  selector: 'app-create-rifas',
  templateUrl: './create-rifas.component.html',
  styleUrls: ['./create-rifas.component.scss'],


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
  divInformation:boolean=true;
  divCreate:boolean=false;
  opcPublicar:boolean=false;
  

  divUpdated:boolean=false;

  
  boletosVender:number=0;
  gananciaVenta:number=0;
  gananciamenosPP:number=0;
  gananciaPorcentajeEmpresa:number=0;
  gananciaPorcentajeUsuario:number=0;
  gananciaUsuario:number=0;
  totalPorcentajes:number=0;
  gananciaFinalUsuario:number=0;
  gananciaFinalconCostoPremio:number=0;

 


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
   
    
    this.CalcularGanancias(this.numBoletos);


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
  
    //Ganancia dinal para usuario sumando el costo del premio
    this.gananciaFinalconCostoPremio=this.gananciaFinalUsuario+this.precioTotalpremio;

  }


 


  CrearRifa(){
    this.divCreate=true;
    this.divInformation=false;
    this.divUpdated=false;
    this.opcPublicar=false;

  }




  VerInfoGanancias(){
    this.divCreate=false;
    this.divInformation=true;
    this.divUpdated=false;
    this.opcPublicar=false;
  }




  VerSeccionPremios(){
    this.divCreate=false;
    this.divInformation=false;
    this.divUpdated=true;
    this.opcPublicar=false;
  }




  PublicarRifa(){
    this.opcPublicar=true;
    this.divCreate=false;
    this.divInformation=false;
    this.divUpdated=false;
  }
  public archivos: NgxFileDropEntry[] = [];
  public archivosAgregados: string[] = [];

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
   
   
   // this.files= files;
    this.previewImages();
  }
  
  private actualizarLista() {
    this.archivosEnLista = this.archivosAgregados.join(' - ');
  }
  /* for (const droppedFile of files) {

      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {

          // Here you can access the real file
          console.log(droppedFile.relativePath, file);

          /**
          // You could upload it like this:
          const formData = new FormData()
          formData.append('logo', file, relativePath)

          // Headers
          const headers = new HttpHeaders({
            'security-token': 'mytoken'
          })

          this.http.post('https://mybackend.com/api/upload/sanitize-and-save-logo', formData, { headers: headers, responseType: 'blob' })
          .subscribe(data => {
            // Sanitized logo returned from backend
          })
      

        });
      } else {
        // It was a directory (empty directories are added, otherwise only files)
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(droppedFile.relativePath, fileEntry);
      }
    }
  }*****/

  public fileOver(event: any){
    console.log(event);
  }

  public fileLeave(event: any){
    console.log(event);
  }




  private previewImages() {
    console.log(this.files)
    for (const droppedFile of this.files) {
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {
          const reader = new FileReader();
          reader.onload = (e: any) => {
            const imgSrc = e.target.result;
            console.log(imgSrc);

            // Aquí puedes mostrar la vista previa en la interfaz de usuario
          };
          reader.readAsDataURL(file);
        });
      } else {
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(fileEntry);
      }
    }
  }

  
}

