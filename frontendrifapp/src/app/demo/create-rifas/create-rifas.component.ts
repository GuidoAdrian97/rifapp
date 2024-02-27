import { Component, HostListener, OnInit, TemplateRef } from '@angular/core';
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
  premios: any[]; 
}


/*interface Premio {
  id:number;
  nombre: string;
  descripcion: string;
  estado: any[];
  categorias: any[]; // Ids de categorías seleccionadas
  imagenes: string[]; // URLs de imágenes
}*/


interface Premio1 {
name_prize: string,
descripcion_prize:string,
calidad:number,
categorias:string,
posicion_prize:number,
imagenes: string[]; // URLs de imágenes
}
/*


*/


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


// Definir un objeto para almacenar datos
//premiosData: Premio[] = [];

premiosData1: any[] = [];


  constructor(private serviceRifa: RifasService,private modalService: BsModalService) {
  
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

  guardarDatos1(){
    if(this.opcLoteria != 0){
      this.rangoFinal = this.opcLoteria;
      this.rangoInicial = 0
      this.numBoletos = this.opcLoteria + 1;
    }
    /*
    let datos:Rifa = {
      'title':this.tituloRifa,
      'rango_inicial_boletos':this.rangoInicial,
      'rango_final_boletos':this.rangoFinal,
      'metodo_sorteo_id':this.opcSorteo,
      'fecha_sorteo_rifa':this.fechaSorteo,
      'description':this.descripRifa,
      'costo_boleto':this.precioRifaboleto,
      'cantidad_boletos':this.numBoletos,
      premios: {
      'name_prize': this.nombrePremio,
      'descripcion_prize':this.descripcionPremio,
      'calidad':this.estadoPremio,
      'categorias':this.categoriaPremio,
      'posicion_prize':this.ordenPremio,
      imagenes: this.imagePreviews,
      
        
      }
      
    }
    debugger
    this.serviceRifa.guardarRifa(datos).subscribe({
      next:rest=>{
        debugger
      },error:error =>{
        console.log(error)
      }
    })*/

    this.VerSeccionPremios();
  }




/*
  guardarDatos(){
    /*if(this.opcLoteria != 0){
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
      /*premios: {
      'name_prize': this.premiosData1,
      'descripcion_prize':this.descripcionPremio,
      'calidad':this.estadoPremio,
      'categorias':this.categoriaPremio,
      'posicion_prize':this.ordenPremio,
      imagenes: this.imagePreviews,
      
        
      }

      
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


  }*/


guardarDatos(){
  
    const data = {
      title: 'Titulo prueba',
      rango_inicial_boletos: 1,
      rango_final_boletos: 29,
      metodo_sorteo_id: 2,
      fecha_sorteo_rifa: '2024-03-01',
      description: 'ghjghjg',
      costo_boleto: 1,
      cantidad_boletos: 29,
      premios: [
        {
          name_prize: 'gf',
          descripcion_prize: 'gfhgfh',
          calidad: 2,
          categorias: 'hogar',
          imagenes: [
            'data:image/jpeg;base64,/9j/'
          ],
          posicion_prize: 1
        },
        
      ]
    };

    this.serviceRifa.guardarRifa(data).subscribe(response => {
      console.log(response);
    });
  
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


//prueba de registrar premios

imgNombres:any[]=[];


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
  console.log(this.premiosData1);
  /*console.log(this.selectedOptions);
  console.log(this.imagePreviews);
  console.log(this.files);*/
  this.modalRef?.hide()
  this.LimpiarDatosPremio();
}

/*obtenerPremios(): Premio[] {
  return this.premiosData;
}*/
 
nombrePremio:string='';
descripcionPremio:string='';
estadoPremio:number=0;
idPremio:number=0;



//prueba para visualizar iconos de estado
iconos = [
  { valor: 1,icon:['fa fa-star']},
  { valor: 2,icon:['fa fa-star','fa fa-star'] },
  { valor: 3,icon:['fa fa-star','fa fa-star','fa fa-star'] },
  { valor: 4,icon:['fa fa-star','fa fa-star','fa fa-star','fa fa-star'] },
  { valor: 4.5,icon:['fa fa-star','fa fa-star','fa fa-star','fa fa-star','fa fa-star-half'] },
  { valor: 5,icon:['fa fa-star','fa fa-star','fa fa-star','fa fa-star','fa fa-star'] },

 
];


selectIconos: any[] = [];



agregarIconos(estado: any) {

if(this.selectIconos.length==1){
//eliminar el registro existente y agregar el nuevo
this.selectIconos.splice(estado, 1);
this.selectIconos.push(estado);

  }else{
    this.selectIconos.push(estado);
  }

  console.log(this.selectIconos);
}




/*guardarPremio(): void {

  // Obtener datos del formulario y agregar premio al servicio
  this.idPremio=this.idPremio+1;
  const nuevoPremio: Premio = {
    id:this.idPremio,
    nombre: this.nombrePremio,
    descripcion: this.descripcionPremio,
    estado: this.selectIconos,
    categorias: this.selectedOptions, // [1, 2] IDs de categorías seleccionadas
    imagenes: this.imagePreviews, // ['url1', 'url2'] URLs de imágenes - relativePath
  };

  this.agregarPremio(nuevoPremio);
 
}*/

categoriaPremio:string='';
ordenPremio:number=0;

guardarPremio(): void {


  const nuevoPremio: any = {

    name_prize: this.nombrePremio,
    descripcion_prize: this.descripcionPremio,
    calidad: this.estadoPremio,
    categorias: this.categoriaPremio, // [1, 2] IDs de categorías seleccionadas
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

 //this.obtenerPremioPorId(numId);

}

LimpiarDatosPremio(){
this.nombrePremio='';
this.descripcionPremio='';
this.estadoPremio=0;
this.selectedOptions=[];
this.imagePreviews=[];
this.selectIconos=[];
 }
   
 valorEstado:number=0;
 nombrePremio1:string='';
 descripcionPremio1:string='';
 estadoPremio1:number=0;
 idPremio1:number=0;
 selectIconos1: any[] = [];
 public imagePreviews1: string[] = [];
 selectedOptions1: any[] = [];



 /*obtenerPremioPorId(id: number): Premio | any {

  const premioEncontrado = this.premiosData1.find(premio => premio.id === id);
  

  if (premioEncontrado) {
    this.idPremio1 = premioEncontrado.id;
    this.nombrePremio1 = premioEncontrado.nombre;
    this.descripcionPremio1 = premioEncontrado.descripcion;
    this.selectIconos1 = premioEncontrado.estado;
    this.valorEstado = premioEncontrado.estado[0].valor;
    this.selectedOptions1 = premioEncontrado.categorias;
    this.imagePreviews1 = premioEncontrado.imagenes;
    
  } else {
    console.log(`No se encontró ningún premio con el ID ${id}`);
  }


  
}*/

isSelectedIcon(icon: any): boolean {
  return icon.valor === this.valorEstado;
}






}

