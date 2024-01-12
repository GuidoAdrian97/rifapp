import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { ColorPickerModule } from 'ngx-color-picker';
import { NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [NgxSpinnerModule,CommonModule, SharedModule, RouterModule, 
    FormsModule, NgbDropdownModule, ColorPickerModule],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export default class SignUpComponent {

  constructor(private authService:AuthService, private router: Router,private spinner: NgxSpinnerService){
    const fechaActual = new Date();
    const fechaMinima = new Date(fechaActual.getFullYear() - 18, fechaActual.getMonth(), fechaActual.getDate() + 1);
    

    this.maxFechaPermitida = fechaMinima.toISOString().split('T')[0];
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


  limitarLongitud(event: any,typeDato:any) {
    const valorIngresado: string = event.target.value;
    if(typeDato == 'dni'){
      if(valorIngresado.length == 10){
        this.loader = true;
        this.validarDatos(typeDato);
      }else{
        this.name = "";
        this.nameRegister = false;
      }
    }
    if(typeDato=='tlf'){
      if(valorIngresado.length == 10){
        this.validarDatos(typeDato);
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
        this.messageTlf="Ingrese un número de cédula valido porfavor";
      }
    }
  

  verificarEstructEmail(typeDato:any){
  
    const regexCorreo = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

    if (!regexCorreo.test(this.email) && this.email.length !=0) {
      console.log('Correo electrónico no válido');
      this.emailError = true
      this.messageEmail = 'Correo electrónico no válido'
    } else {
      console.log('Correo electrónico válido');
      this.emailError = false
      this.validarDatos(typeDato);
    }
  }

  // validarEdad(){
  //   const fechaNacimiento = new Date(this.fecha_nacimiento);
  //   const fechaActual = new Date();
  //   const edad = fechaActual.getFullYear() - fechaNacimiento.getFullYear();
  //   console.log(edad)
  //   if(edad < 18){
  //     this.fecha_nacimiento="";
  //     this.fechaError=true;
  //     this.messageFecha="Debe ser mayor de edad para continuar."
  //   }else{
  //     this.fechaError=false;
  //   }
  // }

  name:string=""; identificacion:string=""; telefono:string=""; fecha_nacimiento:string=""; email:string=""; password:string="";
  crearCuenta(){
    this.spinner.show();
    let data = {
      name:this.name,
      identificacion:this.identificacion,
      telefono:this.telefono,
      fecha_nacimiento:this.fecha_nacimiento,
      email:this.email,
      password:this.password
    }
    this.authService.authUser(data).subscribe({
      next: rest => {
        console.log(rest);
          this.spinner.hide();
        this.router.navigate(['/auth/signin']);
      },error:error =>{
        console.log(error);
        this.spinner.hide();  
      }
    })
  }

  updateNombre(){
    this.authService.validarNombre(this.identificacion).subscribe({
      next:rest=>{
        console.log(rest)
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
        console.log(rest)
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
      console.log('Contraseña válida');
      this.inputsValidado();
    }
  }

  inputsValidado(){
    if(this.identificacion != '' && this.dniError == false && this.name != '' 
    && this.fecha_nacimiento != ''  && this.telefono != ''
    && this.tlfError == false && this.email != '' && this.emailError ==false 
    && this.password != '' && this.passwordError == false){
      this.btnDisabled = false;
    }else{
      this.btnDisabled = true;
    }
  }


 
 
}
