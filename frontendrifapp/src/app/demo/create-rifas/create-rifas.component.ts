import { Component, HostListener, OnInit, TemplateRef } from '@angular/core';
import { NgbCalendar, NgbDate, NgbDatepickerConfig, NgbDatepickerI18n } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectConfig } from '@ng-select/ng-select';
import { iteratee } from 'lodash';

import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';

import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { relative } from 'path';
import { elementAt } from 'rxjs';
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
  premios: Premio[]; 
}


/*interface Premio {
  id:number;
  nombre: string;
  descripcion: string;
  estado: any[];
  categorias: any[]; // Ids de categorías seleccionadas
  imagenes: string[]; // URLs de imágenes
}*/


interface Premio {
name_prize: string,
descripcion_prize:string,
//calidad:number,
//categorias:string,
calidad: any[];
categorias: any[]; // Ids de categorías seleccionadas
posicion_prize:number,
imagenes: string[]; // URLs de imágenes
}
/*


*/
@Component({
  selector: 'app-create-rifas',
  templateUrl: './create-rifas.component.html',
  styleUrls: ['./create-rifas.component.scss']
})
export class CreateRifasComponent implements OnInit {

  // fechaSeleccionada: NgbDate | null = null;

  // isDisabled = (date: NgbDate) => {
  //   const dayOfWeek = this.calendar.getWeekday(date);
  //   return ![1, 3, 5].includes(dayOfWeek); // Solo habilita lunes, miércoles y viernes
  // };

  // customDay = (date: NgbDate, current: { month: number }) => {
  //   return this.isDisabled(date) ? 'custom-day-disabled' : undefined;
  // };

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
  typeCategories:any;
  valorStar:number=0;



 


// Definir un objeto para almacenar datos
//premiosData: Premio[] = [];

premiosData1: any[] = [];
getCurrentDate(): string {
  const currentDate = new Date();
  return currentDate.toISOString().split('T')[0];
}

  constructor(private serviceRifa: RifasService,private modalService: BsModalService,
    private calendar: NgbCalendar, private config: NgbDatepickerConfig) {
    //   this.config.minDate = { year: 1900, month: 1, day: 1 };
    // this.config.maxDate = { year: 2099, month: 12, day: 31 };
    this.serviceRifa.tipoSorteo().subscribe({
      next:rest =>{
        
        this.typeRifa = rest['Metodos de Sorteo'];
        console.log(this.typeRifa)
      },error:error => {
        console.log(error)
      }
    });

    this.serviceRifa.tipoCategorias().subscribe({
      next:rest =>{
     
        this.typeCategories = rest['prizecates'];
      
        console.log(this.typeCategories)
      },error:error => {
        console.log(error)
      }
    })


  }


  resaltar:any;
  ngOnInit() {
    this.resaltar = true;

    // Puedes agregar un tiempo de espera para desactivar la animación después de un tiempo
    setTimeout(() => {
      this.resaltar = false;
    }, 10000); // Tiempo en milisegundos (igual a la duración de la animación)
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
      'cantidad_boletos':this.numBoletos,
      premios:this.premiosData1
    }
    console.log(datos);
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




  validatedOpcSorteo1 = 'form-control';
  validatedPrecioTotalPremio1 = 'form-control';
  validatedPrecioRifaBoleto1 = 'form-control';
  clickConsultar = false;


  ValidatedForm1(){
    this.validatedOpcSorteo1 = 'form-control is-invalid';
      this.validatedPrecioTotalPremio1 = 'form-control is-invalid';
      this.validatedPrecioRifaBoleto1 = 'form-control is-invalid';
  }



  CrearRifa() {
    if(this.precioTotalpremio > 0 && this.precioRifaboleto > 0 && this.minBoletos > 0 && this.opcSorteo > 0 ){
      this.divCreate = true;
    this.divInformation = false;
    this.divUpdated = false;
    this.opcPublicar = false;
    this.onInputChange();
    this.scrollToTop();
    }else{
      this.ValidatedForm1();
    }
  }




  onInputChange() {
    this.rangoInicial = 1; this.rangoFinal = this.numBoletos;
    // Aquí puedes realizar acciones adicionales con el nuevo valor del input
  }




  VerInfoGanancias() {
    this.divCreate = false;
    this.divInformation = true;
    this.divUpdated = false;
    this.opcPublicar = false;
  }



  validatedFechaSorteo = 'form-control';
  validatedTituloRifa = 'form-control';
  validatedDescripcionRifa = 'form-control';
  validatedSelectCantidadBoleto = 'form-control';


  validatedForm2_1(){
    this.validatedFechaSorteo = 'form-control is-invalid';
    this.validatedTituloRifa = 'form-control is-invalid';
    this.validatedDescripcionRifa = 'form-control is-invalid';
    this.validatedSelectCantidadBoleto = 'form-control is-invalid';
    
  }

  
  VerSeccionPremios() {
    if (this.precioTotalpremio > 0 && this.precioRifaboleto > 0 && this.minBoletos > 0 && this.opcSorteo > 0) {
      if (this.opcSorteo == 1) {
        if (this.fechaSorteo != '' && this.tituloRifa != '' && this.descripRifa != '' && this.opcLoteria != 0) {
          this.divCreate = false;
          this.divInformation = false;
          this.divUpdated = true;
          this.opcPublicar = false;
        }else{
          this.validatedForm2_1();
        }
      }else{
        if (this.fechaSorteo != '' && this.tituloRifa != '' && this.descripRifa != '' && this.numBoletos != 0 ) {
          this.divCreate = false;
          this.divInformation = false;
          this.divUpdated = true;
          this.opcPublicar = false;
        }
      }
    }else{
      this.ValidatedForm1();
    }
  }


//elimianr esto
  options = [
    { id: 1, name: 'Hogar' },
    { id: 2, name: 'Efectivo' },
    { id: 3, name: 'Vehículos' },
    { id: 4, name: 'Tecnología' },
    { id: 5, name: 'Otra' },
  
  ];



  selectedOptions:any[]=[];
 


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
    if (this.precioTotalpremio > 0 && this.precioRifaboleto > 0 && this.minBoletos > 0 && this.opcSorteo > 0) {

      if (this.opcSorteo == 1) {
        if (this.fechaSorteo != '' && this.tituloRifa != '' && this.descripRifa != '' && this.opcLoteria != 0) {
          this.opcPublicar = true;
          this.divCreate = false;
          this.divInformation = false;
          this.divUpdated = false;
        }
      } else {
        if (this.fechaSorteo != '' && this.tituloRifa != '' && this.descripRifa != '' && this.numBoletos != 0) {
          this.opcPublicar = true;
          this.divCreate = false;
          this.divInformation = false;
          this.divUpdated = false;
        }
      }
    }else{
      this.ValidatedForm1();
    }
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




/*agregarPremio(premio: Premio): void {
  this.premiosData.push(premio);
  console.log(this.premiosData);
  /*console.log(this.selectedOptions);
  console.log(this.imagePreviews);
  console.log(this.files);*/
 /* this.modalRef?.hide()
  this.LimpiarDatosPremio();
}*/


agregarPremio(premio: any): void {
  this.premiosData1.push(premio);
  //console.log(this.premiosData1);
  this.modalRef?.hide()
  this.LimpiarDatosPremio();
}



 
nombrePremio:string='';
descripcionPremio:string='';




//prueba para visualizar iconos de calidad de premio
iconos = [
  { valor: 0.5,icon:['fa fa-star-half']},
  { valor: 1,icon:['fa fa-star']},
  { valor: 1.5,icon:['fa fa-star','fa fa-star-half']},
  { valor: 2,icon:['fa fa-star','fa fa-star']},
  { valor: 2.5,icon:['fa fa-star','fa fa-star','fa fa-star-half']},
  { valor: 3,icon:['fa fa-star','fa fa-star','fa fa-star'] },
  { valor: 3.5,icon:['fa fa-star','fa fa-star','fa fa-star','fa fa-star-half']},
  { valor: 4,icon:['fa fa-star','fa fa-star','fa fa-star','fa fa-star'] },
  { valor: 4.5,icon:['fa fa-star','fa fa-star','fa fa-star','fa fa-star','fa fa-star-half'] },
  { valor: 5,icon:['fa fa-star','fa fa-star','fa fa-star','fa fa-star','fa fa-star'] },

];


selectIconos: any[] = [];




//agregarIconos(estado: any) {
agregarIconos(n: number) {

if(this.selectIconos.length==1){
//eliminar el registro existente y agregar el nuevo
this.selectIconos=[];
this.selectIconos.push(this.iconos.find(item => item.valor === n));
//this.selectIconos.splice(estado, 1);
//this.selectIconos.push(estado);

  }else{
    //this.selectIconos.push(estado);
    this.selectIconos.push(this.iconos.find(item => item.valor === n));
  }

}




ordenPremio:number=0;

guardarPremio(): void {

  this.ordenPremio=this.ordenPremio+1;

  const nuevoPremio: any = {

    name_prize: this.nombrePremio,
    descripcion_prize: this.descripcionPremio,
    //calidad: this.estadoPremio,
    calidad:this.selectIconos,
    categorias: this.selectedOptions, // [1, 2] IDs de categorías seleccionadas
    imagenes: this.imagePreviews, // ['url1', 'url2'] URLs de imágenes - relativePath
    posicion_prize:this.ordenPremio,
  };

  this.agregarPremio(nuevoPremio);
 
 
}



 
modalRef?: BsModalRef | null;
modalRef1?: BsModalRef ;

openModal(template: TemplateRef<void>) {
  this.modalRef = this.modalService.show(template, { id: 5, class: 'modal-lg' });
}


openModal1(template: TemplateRef<void>,numId:number) {
  this.modalRef1 = this.modalService.show(template, { id: 6, class: 'modal-lg' });

 this.obtenerPremioPorId(numId);

}


LimpiarDatosPremio(){
this.nombrePremio='';
this.descripcionPremio='';
this.selectedOptions=[];
this.imagePreviews=[];
this.selectIconos=[];
this.valorStar=0;

 }
   


 nombrePremio1:string='';
 descripcionPremio1:string='';
 estadoPremio1:number=0;
 ordenPremio1:number=0;
 selectIconos1: any[] = [];
 public imagePreviews1: string[] = [];
 public files1: NgxFileDropEntry[] = [];
 selectedOptions1: any[] = [];
 valorStar1:number=0;


 public dropped1(files1: NgxFileDropEntry[]) {
  this.files1 = files1;

  for (const droppedFile of files1) {

    if (droppedFile.fileEntry.isFile) {
      const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
      fileEntry.file((file: File) => {
        // Lee el contenido de la imagen y muestra la vista previa
        const reader = new FileReader();
        reader.onload = (e:any) => {
          this.imagePreviews1.push(e.target.result as string);
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

public fileOver1(event: any) {
  console.log(event);
}

public fileLeave1(event: any) {
  console.log(event);
}

EliminarImagen1(index:number){
  this.imagePreviews1.splice(index, 1);
}








 obtenerPremioPorId(id: number): Premio | any {

  const premioEncontrado = this.premiosData1.find(premio => premio.posicion_prize === id);
  

  if (premioEncontrado) {
    this.ordenPremio1 = premioEncontrado.posicion_prize;
    this.nombrePremio1 = premioEncontrado.name_prize;
    this.descripcionPremio1 = premioEncontrado.descripcion_prize;
   this.valorStar1 = premioEncontrado.calidad[0].valor;
    this.selectedOptions1 = premioEncontrado.categorias;
    this.imagePreviews1 = premioEncontrado.imagenes;
    
  } else {
    console.log(`No se encontró ningún premio con el ID ${id}`);
  }



  
}


editarPremio(orden:number){
  const premioEncontrado = this.premiosData1.find(premio => premio.posicion_prize === orden);
  

  if (premioEncontrado) {
     premioEncontrado.posicion_prize=this.ordenPremio1;
     premioEncontrado.name_prize=this.nombrePremio1;
     premioEncontrado.descripcion_prize=this.descripcionPremio1;
     premioEncontrado.calidad[0].valor= this.valorStar1;
     premioEncontrado.categorias= this.selectedOptions1;
     premioEncontrado.imagenes=this.imagePreviews1;
    
  } else {
    console.log(`No se encontró ningún premio con el ID ${orden}`);
  }

  
  this.modalRef1?.hide()
}


eliminarPremio(orden:number){
  const premioEncontrado = this.premiosData1.find(premio => premio.posicion_prize === orden);

  this.premiosData1.splice(premioEncontrado, 1);
  console.log(this.premiosData1);
    
  this.modalRef1?.hide()
}


/*prueba para seleccionar calidad de premio con estrellas
stars = Array(5).fill(0).map((_, index) => index + 1);
selectedRating = 0;

rateProduct(rating: number): void {
  this.selectedRating = rating;
}
*/







}

