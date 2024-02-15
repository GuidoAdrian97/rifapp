import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { ColorPickerModule } from 'ngx-color-picker';
import { NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [NgxSpinnerModule,CommonModule, SharedModule, RouterModule, 
    FormsModule, NgbDropdownModule, ColorPickerModule],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})

export default class SignUpComponent {

  constructor(private authService:AuthService,private route:ActivatedRoute, private router: Router,private spinner: NgxSpinnerService){
    const fechaActual = new Date();
    const fechaMinima = new Date(fechaActual.getFullYear() - 18, fechaActual.getMonth(), fechaActual.getDate() + 1);
    this.maxFechaPermitida = fechaMinima.toISOString().split('T')[0];
    const parametroId = this.route.snapshot.params['referrerCode'];
    if(parametroId){
      this.referrerCode = parametroId;
      this.validarCodigo(parametroId)
    }else{
      this.authService.referidoPrincipal().subscribe({
        next:rest =>{
          this.referidoPrincipal = rest.ReferidoCode;
          this.referrerCode = this.referidoPrincipal;
        },error:error =>{
          this.onError();
        }
      })
    }
    
    sessionStorage.clear();
    localStorage.clear();
  }

  dni:string="";

  dniError=false;
  messageDni:string ="";
  nameRegister:boolean=false;
  loader=false;
  
  messageTlf:string = "";
  tlfError:boolean = false;
  messageEmail:string = "";
  emailError:boolean = false;
  messagePassword:string = "";
  passwordError:boolean = false;
  maxFechaPermitida: string;
  btnDisabled:boolean = true;
  messageCodigo:string="";
  codigoError:boolean = false;
  habilitarCode:boolean = true


  limitarLongitud(event: any,typeDato:any) {
    
    const valorIngresado: string = event.target.value;
    if(typeDato == 'dni'){
      if(valorIngresado.length == 10){
        this.loader = true;
        this.validarDatos(typeDato);
      }else{
        this.name = "";
        this.nameRegister = false;
        this.dniError = true;
        this.messageDni = "Ingrese un número de cédula valido porfavor"
        this.inputsValidado();
      }
    }
    if(typeDato=='tlf'){
      if(valorIngresado.length == 10){
        this.validarDatos(typeDato);
      }else{
        this.tlfError = true;
        this.messageTlf = "Ingrese un número de teléfono valido porfavor"
        this.inputsValidado();
      }
    }
  }

  numValidated(event: any,input:any){
    const valorIngresado: string = event.target.value;
      if(valorIngresado.length != 10 && valorIngresado.length != 0 && input == 'dni'){
        this.dniError = true;
        this.messageDni="Ingrese un número de cédula valido porfavor";
      }else if(valorIngresado.length != 10 && valorIngresado.length != 0 && input == 'tlf'){
        this.tlfError = true;
        this.messageTlf="Ingrese un número de teléfono valido porfavor";
      }

      if (!(/^[0-9]{10}$/.test(this.telefono)) && this.telefono.length !=0 ) {
        this.tlfError = true;
        this.messageTlf="Ingrese un número de teléfono valido porfavor";
      } else {
        this.tlfError = false
      }
      this.inputsValidado()
    }
  

  verificarEstructEmail(typeDato:any){
  
    const regexCorreo = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

    if (!regexCorreo.test(this.email) && this.email.length !=0) {
      this.emailError = true
      this.messageEmail = 'Correo electrónico no válido'
    } else {
      this.emailError = false
      this.validarDatos(typeDato);
    }
  }

  name:string=""; identificacion:string=""; telefono:string=""; fecha_nacimiento:string=""; email:string=""; password:string="";
  referrerCode:string = ""; referidoPrincipal:string = "";
  onSuccess() {
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'Tu cuenta se ha credado correctamente',
      showConfirmButton: false,
      timer: 1000
    });
  }

  onError() {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Algo salío mal. Reintentalo más tarde!',
    });
  }
  crearCuenta(){
    this.spinner.show();
    let data = {
      name:this.name,
      identificacion:this.identificacion,
      telefono:this.telefono,
      fecha_nacimiento:this.fecha_nacimiento,
      email:this.email,
      password:this.password,
      referrerCode:this.referrerCode
    }
    this.authService.authUser(data).subscribe({
      next: rest => {
          this.spinner.hide();
          this.onSuccess();
        this.router.navigate(['/auth/signin']);
      },error:error =>{
        this.onError();
        this.spinner.hide();  
      }
    })
  }

  updateNombre(){
    this.authService.validarNombre(this.identificacion).subscribe({
      next:rest=>{
        if(rest.nombre){
        this.name = rest.nombre
        this.dniError = false
        this.nameRegister = true
        this.loader = false;
        }else{
          this.dniError = true;
        this.messageDni="Ingrese un número de cédula valido porfavor";
        
        this.loader = false;
        }

        this.inputsValidado();
        
      },error:error=>{
        console.log(error.error.message)
      }
    })
  }

  validarDatos(input:any){
    this.authService.validarDatos(this.identificacion,this.telefono,this.email).subscribe({
      next:rest =>{
        if(input == 'dni' && rest.menssage){
          this.dniError = true;
          this.loader = false;
          this.messageDni = "El número que ingresó ya se encuentra registrado."
        }else if(input == 'dni'){
          this.updateNombre();
        }

        if(input == 'tlf' && rest.menssage){
          this.tlfError = true;
          this.messageTlf = "El número que ingresó ya se encuentra registrado."
        }else if(input == 'tlf'){
          this.tlfError = false
          this.inputsValidado();
        }

        if(input == 'email' && rest.menssage){
          this.emailError = true;
          this.messageEmail = "El correo que ingresó ya se encuentra registrado."
        }else if(input == 'tlf'){
          this.emailError = false
          this.inputsValidado();
        }

      },error:error=>{
        console.log(error)
      }
    })
  }

  validarPassword(){
    // Validar la contraseña
    if (!this.password) {
      this.passwordError = true
      this.messagePassword = 'La contraseña es obligatoria.';
    } else if (this.password.length < 8) {
      this.passwordError=true
      this.messagePassword = 'La contraseña debe tener al menos 8 caracteres.';
    } else {
      this.passwordError=false
      this.inputsValidado();
    }
  }

  inputsValidado(){
    if(this.identificacion != '' && this.dniError == false && this.name != '' 
    && this.fecha_nacimiento != ''  && this.telefono != ''
    && this.tlfError == false && this.email != '' && this.emailError ==false 
    && this.password != '' && this.passwordError == false && this.codigoError == false && this.referrerCode != ''){
      
      this.btnDisabled = false;
    }else{
      
      this.btnDisabled = true;
    }
  }

  codValidated(event:any){
    const referrerCode = event.target.value;
    if(referrerCode == ""){
      this.codigoError = true;
      this.messageCodigo = "Ingrese un codigo de referido";
    }else {
      this.validarCodigo(referrerCode);
    }
    
  }

  validarCodigo(referrerCode:any){
    this.authService.validarCodigoReferencia(referrerCode).subscribe({
      next:rest =>{
        if(rest.error){
          
          this.codigoError = true
          this.messageCodigo = rest.error;
        }else{
          this.codigoError = false;
        }
        this.inputsValidado();
      },error : error => {
        this.onError();
      }
    })
  }

  funcion(){
    this.referrerCode = "";
    this.inputsValidado();
    if(this.habilitarCode == false){
      this.referrerCode = this.referidoPrincipal;
      this.codigoError = false
      this.inputsValidado();
    }
    const parametroId = this.route.snapshot.params['referrerCode'];
    if(parametroId && !this.habilitarCode){
      this.referrerCode = parametroId;
      this.validarCodigo(parametroId);
    }
    this.habilitarCode = !this.habilitarCode 
  }



 
 
}
