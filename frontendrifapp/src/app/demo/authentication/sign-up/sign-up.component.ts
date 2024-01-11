import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, SharedModule, RouterModule, FormsModule],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export default class SignUpComponent {

  constructor(private authService:AuthService){}

  dni:string="";

  limitarLongitud(event: any,typeDato:any) {
    const valorIngresado: string = event.target.value;
    if(typeDato == 'dni'){
      if(valorIngresado.length == 10){
        this.validarDatos(typeDato);
      }else{
        this.name = "";
      }
    }
    if(typeDato=='tlf'){
      if(valorIngresado.length == 10){
        this.validarDatos(typeDato);
      }
    }
  }

  name:string=""; identificacion:string=""; telefono:string=""; fecha_nacimiento:string=""; email:string=""; password:string="";
  crearCuenta(){
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
      },error:error =>{
        console.log(error);
      }
    })
  }

  updateNombre(identificacion:any){
    this.authService.validarNombre(identificacion).subscribe({
      next:rest=>{
        console.log(rest)
        this.name = rest.nombre
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
          this.identificacion = "";
        }else{
          this.authService.validarNombre(this.identificacion).subscribe({
            next:rest =>{
              this.name=rest.nombre;
            },error:error=>{
              console.log(error);
            }
          });
        }

        if(input == 'tlf' && rest.menssage){
          this.telefono = "";
        }

        if(input == 'email' && rest.menssage){
          
          this.email = "";
        }

      },error:error=>{
        console.log(error)
      }
    })
  }

}
