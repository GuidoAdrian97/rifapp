import { CommonModule } from '@angular/common';
import { Component,OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { ColorPickerModule } from 'ngx-color-picker';
import { NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';
import { AuthGoogleService } from 'src/app/services/auth-google.service';
import { AuthService } from 'src/app/services/auth.service';
import { SharedModule } from 'src/app/theme/shared/shared.module';


@Component({
  selector: 'app-update-datos',
  templateUrl: './update-datos.component.html',
  styleUrls: ['./update-datos.component.scss']
})
export class UpdateDatosComponent implements OnInit {

  constructor(private authService:AuthService, private authGoogleService:AuthGoogleService,
    private router: Router,private spinner: NgxSpinnerService){
    const fechaActual = new Date();
    const fechaMinima = new Date(fechaActual.getFullYear() - 18, fechaActual.getMonth(), fechaActual.getDate() + 1);
    this.maxFechaPermitida = fechaMinima.toISOString().split('T')[0];
      
      this.spinner.hide();
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

  ngOnInit():void{
    const jsonString :any = sessionStorage.getItem('id_token_claims_obj')!
    const jsonObject = JSON.parse(jsonString);
    this.emailUpdated = jsonObject.email
  }

  limitarLongitud(event: any,typeDato:any) {
    const valorIngresado: string = event.target.value;
    if(typeDato == 'dni'){
      if(valorIngresado.length == 10){
        this.loader = true;
        this.validarDatos(typeDato);
      }else{
        this.nameUpdated = "";
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
        this.messageTlf="Ingrese un número de teléfono valido porfavor";
      }
      if (!(/^[0-9]{10}$/.test(this.telefonoUpdated)) && this.telefonoUpdated.length !=0 ) {
        this.tlfError = true;
        this.messageTlf="Ingrese un número de teléfono valido porfavor";
      } else {
        this.tlfError = false
      }
    }

    verificarEstructEmail(typeDato:any){
      const regexCorreo = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      if (!regexCorreo.test(this.emailUpdated) && this.emailUpdated.length !=0) {
        console.log('Correo electrónico no válido');
        this.emailError = true
        this.messageEmail = 'Correo electrónico no válido'
      } else {
        console.log('Correo electrónico válido');
        this.emailError = false
        this.validarDatos(typeDato);
      }
    }
  

  nameUpdated:string=""; 
  identificacionUpdated:string="";
  telefonoUpdated:string=""; 
  fecha_nacimientoUpdated:string="";
  emailUpdated:string ="";
  passwordUpdated:string="";

 
  actualizarCuenta(){
    this.spinner.show()
    let data = {
      accessToken: sessionStorage.getItem('id_token'),
      identificacion:this.identificacionUpdated,
      telefono:this.telefonoUpdated,
      fecha_nacimiento:this.fecha_nacimientoUpdated,
      password:this.passwordUpdated,
      name:this.nameUpdated,
    }

    this.authService.updateregistersocialite(data).subscribe({
      next:rest => {
        this.router.navigate(['/Inicio']);
      },error:error=>{
        this.logout();
      }
    })
  }

  logout(){
    localStorage.clear();
    this.authGoogleService.logout();
    this.router.navigate(['/'])
  }
  updateNombre(){
    this.authService.validarNombre(this.identificacionUpdated).subscribe({
      next:rest=>{
        console.log(rest)
        if(rest.nombre){
        this.nameUpdated = rest.nombre
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
    this.authService.validarDatos(this.identificacionUpdated,this.telefonoUpdated,this.emailUpdated).subscribe({
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
    if (!this.passwordUpdated) {
      this.passwordError = true
      this.messagePassword = 'La contraseña es obligatoria.';
    } else if (this.passwordUpdated.length < 8) {
      this.passwordError=true
      this.messagePassword = 'La contraseña debe tener al menos 8 caracteres.';
    } else {
      this.passwordError=false
      console.log('Contraseña válida');
      this.inputsValidado();
    }
  }

  inputsValidado(){
    if(this.identificacionUpdated != '' && this.dniError == false && this.nameUpdated != '' 
    && this.fecha_nacimientoUpdated != ''  && this.telefonoUpdated != ''
    && this.tlfError == false && this.emailUpdated != '' && this.emailError ==false 
    && this.passwordUpdated != '' && this.passwordError == false){
      this.btnDisabled = false;
    }else{
      this.btnDisabled = true;
    }
  }



}

