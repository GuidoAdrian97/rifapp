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

}
