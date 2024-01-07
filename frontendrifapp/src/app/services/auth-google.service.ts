import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthConfig, OAuthService } from 'angular-oauth2-oidc';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGoogleService {

  constructor(private oAuthService:OAuthService, private httpCliente:HttpClient) { 
    this.initLogin();
  }

  initLogin(){
    const config:AuthConfig ={
      issuer: 'https://accounts.google.com',
      strictDiscoveryDocumentValidation: false,
      clientId:'878790006087-s8pcdmopki16799fojrkho4l7nn1tb47.apps.googleusercontent.com',
      redirectUri:window.location.origin + '/Inicio',
      scope: 'openid profile email',
    }

    this.oAuthService.configure(config);
    this.oAuthService.setupAutomaticSilentRefresh();
    this.oAuthService.loadDiscoveryDocumentAndTryLogin();
  }

  login(){
    this.oAuthService.initLoginFlow();
  }

  logout(){
    this.oAuthService.logOut();
  }

  getProfile(){
    return this.oAuthService.getIdentityClaims();
  }

  authUser(): Observable<any> {
    const tokenid = sessionStorage.getItem('id_token');
    return this.httpCliente.post('http://rifapp.com:8000/api/login/google/callback',{accessToken:tokenid});
  }

}
