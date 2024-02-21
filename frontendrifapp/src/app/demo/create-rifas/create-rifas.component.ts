import { Component, HostListener, OnInit, TemplateRef } from '@angular/core';
import { NgSelectConfig } from '@ng-select/ng-select';



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

  opcSorteo: number = 0;
  fechaSorteo: string = "";
  tituloRifa: string = "";
  descripRifa: string = "";
  intervaloBoletos: number = 0;
  premiosCantidad: number = 0;
  porcentajeMinBoletos: number = 10;
  minBoletosxPersona: number = 0;
  AUX2: number = 0;
  rangoInicial:number=0;
  rangoFinal:number=0;
  opcLoteria:number = 0
  typeRifa:any;
  showButton: any;
premios:boolean=true;



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
    this.screenWidth = window.innerWidth;
    this.screenHeight = window.innerHeight;

  }

  guardarDatos(){
    if(this.opcLoteria != 0){
      this.rangoFinal = this.opcLoteria;
      this.rangoInicial = 0
      this.numBoletos = this.opcLoteria + 1;
    }
    let datos:Rifa = {
      'title':this.tituloRifa,
      'rango_inicial_boletos':this.rangoInicial,
      'rango_final_boletos':this.rangoFinal,
      'metodo_sorteo_id':this.opcSorteo,
      'fecha_sorteo_rifa':this.fechaSorteo,
      'description':this.descripRifa,
      'costo_boleto':this.precioRifaboleto,
      'cantidad_boletos':this.numBoletos
    }
    debugger
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


  scrollToBottom() {
    const windowHeight = window.innerHeight;
    const documentHeight = document.body.scrollHeight;
    const scrollY = window.scrollY;

    // Calcular la posición para desplazarse hacia abajo
    const targetScroll = Math.min(scrollY + windowHeight, documentHeight);

    window.scrollTo({ top: targetScroll, behavior: 'smooth' });
  }


  scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }


  //prueba de verificación de dimensiones de pantalla de dispósitivo
  screenHeight:number=0;
  screenWidth:number=0;


  @HostListener('window:resize', ['$event'])
  onResize(event: Event): void {
    this.screenWidth = window.innerWidth;
    this.screenHeight = window.innerHeight;
 

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

    //agregé esto
   
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
    this.scrollToTop();

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



  options = [
    { id: 1, name: 'Hogar' },
    { id: 2, name: 'Efectivo' },
    { id: 3, name: 'Vehículos' },
    { id: 4, name: 'Tecnología' },
    { id: 5, name: 'Otra' },
  
  ];

  selectedOptions: any[] = [];
 

  onItemSelected(option: any) {
    if (this.isSelected(option)) {
      // Si ya está seleccionado, eliminarlo del arreglo
      //this.selectedOptions = this.selectedOptions.filter(item => item.id !== option.id);
      this.selectedOptions.push(option);
    } else {
      // Si no está seleccionado, agregarlo al arreglo
      //this.selectedOptions.push(option);
      this.selectedOptions = this.selectedOptions.filter(item => item.id !== option.id);
    }
  }

  isSelected(option: any): boolean {
    // Verificar si la opción está en el arreglo de opciones seleccionadas
    return this.selectedOptions.some(item => item.id === option.id);
  }





  PublicarRifa() {
    this.opcPublicar = true;
    this.divCreate = false;
    this.divInformation = false;
    this.divUpdated = false;
  }



  //prueba de agregar archivos y visualizar las imágenes
  public files: NgxFileDropEntry[] = [];
  public imagePreviews: string[] = [];

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;

    for (const droppedFile of files) {

      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {
          // Lee el contenido de la imagen y muestra la vista previa
          const reader = new FileReader();
          reader.onload = (e:any) => {
            this.imagePreviews.push(e.target.result as string);
          };
          reader.readAsDataURL(file);
        });
      } else {
        // Es un directorio (puedes manejarlo si es necesario)
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(fileEntry);
      }
    }
  }

  public fileOver(event: any) {
    console.log(event);
  }

  public fileLeave(event: any) {
    console.log(event);
  }

  EliminarImagen(index:number){
    this.imagePreviews.splice(index, 1);
  }



 
 

 
   


}

