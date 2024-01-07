import { CUSTOM_ELEMENTS_SCHEMA, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/theme/shared/shared.module';
import { RouterModule } from '@angular/router';
import { GoogleserviceService } from 'src/app/services/googleservice.service';
import { Router } from '@angular/router';

import { HttpClient } from '@angular/common/http';
import { AuthGoogleService } from 'src/app/services/auth-google.service';

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
    private http: HttpClient){
  }

  ngOnInit():void {
   
  }

  login(): void {
   this.authGoogleService.login();
  }

  sendTokenToLaravelApi(token: string): void {
    const apiUrl = 'http://your-laravel-api.com/login/google/callback';

    this.http.post(apiUrl, { token }).subscribe(response => {
      console.log("Respuesta del Auth "+response); // Manejar la respuesta de tu API Laravel
    });
  }

  // loginWithGoogle(): void {
  //   this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID);
  // }

  // logOut(): void {
  //   this.socialAuthService.signOut();
  // }

  // probarGoogle(){
  //   this.service.googleLogin().subscribe({
  //     next: rest =>{

  //     },
  //     error:error =>{

  //     }
  //   })
  //   const urlExterna = 'http://rifapp.com:8000/google-auth/redirect'; // Reemplaza con tu URL externa
  //   window.open(urlExterna, '_self');
  // }
}
