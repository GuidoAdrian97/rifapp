import { CUSTOM_ELEMENTS_SCHEMA, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { RouterModule } from '@angular/router';
import { GoogleserviceService } from 'src/app/services/googleservice.service';
import { Router } from '@angular/router';

import { HttpClient } from '@angular/common/http';
import { AuthGoogleService } from 'src/app/services/auth-google.service';
import { AuthService } from 'src/app/services/auth.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [CommonModule, SharedModule, RouterModule],
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export default class SignInComponent implements OnInit{

  constructor(private authGoogleService:AuthGoogleService,
    private authService:AuthService, private router: Router,private spinner: NgxSpinnerService){
      sessionStorage.clear();
    localStorage.clear();
    this.spinner.hide();
  }

  email:string="";
  password:string="";
  emailError:boolean=false;
  messageEmail:string="";
  passwordError:boolean=false;
  messagePassword:string="";
  ngOnInit():void {
   
  }

  verificarEstructEmail(){
    const regexCorreo = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (!regexCorreo.test(this.email) && this.email.length !=0) {
      console.log('Correo electrónico no válido');
      this.emailError = true
      this.messageEmail = 'Correo electrónico no válido'
    } else {
      console.log('Correo electrónico válido');
      this.emailError = false
    }
  }

  validarPassword(){
    this.passwordError = false;
  }

  login(){
    if(this.password.length<8){
      this.passwordError = true
      this.messagePassword="Ingrese una contraseña"
    }

    if(this.email.length < 1){
      this.emailError = true
      this.messageEmail = 'Ingrese un correo electrónico'
    }

    if(this.emailError == false && this.passwordError == false && this.password.length >0 && this.email.length > 0){
      this.spinner.show();
    let data = {
      email:this.email,
      password:this.password
    }
    this.authService.login(data).subscribe({
      next:rest =>{
        console.log('Login:',rest)
        localStorage.setItem('access_token',rest.token)
        debugger
        this.router.navigate(['/Inicio'])
      },error:error=>{
        console.log(error)
        this.spinner.hide();
      }
    })
    } 
  }

  loginGoogle(): void {
   this.authGoogleService.login();
  }

}
