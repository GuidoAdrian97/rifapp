// Angular Import
import { Component, DoCheck, OnInit } from '@angular/core';
import { animate, style, transition, trigger } from '@angular/animations';
import { GradientConfig } from 'src/app/app-config';

// bootstrap
import { NgbDropdownConfig } from '@ng-bootstrap/ng-bootstrap';
import { AuthGoogleService } from 'src/app/services/auth-google.service';
import { Router } from '@angular/router';
import { NgxSpinnerService,NgxSpinnerModule } from 'ngx-spinner';

@Component({
  selector: 'app-nav-right',
  templateUrl: './nav-right.component.html',
  styleUrls: ['./nav-right.component.scss'],
  providers: [NgbDropdownConfig,NgxSpinnerModule],
  animations: [
    trigger('slideInOutLeft', [
      transition(':enter', [style({ transform: 'translateX(100%)' }), animate('300ms ease-in', style({ transform: 'translateX(0%)' }))]),
      transition(':leave', [animate('300ms ease-in', style({ transform: 'translateX(100%)' }))])
    ]),
    trigger('slideInOutRight', [
      transition(':enter', [style({ transform: 'translateX(-100%)' }), animate('300ms ease-in', style({ transform: 'translateX(0%)' }))]),
      transition(':leave', [animate('300ms ease-in', style({ transform: 'translateX(-100%)' }))])
    ])
  ]
})
export class NavRightComponent implements DoCheck, OnInit {
  // public props
  visibleUserList: boolean;
  chatMessage: boolean;
  friendId!: number;
  gradientConfig = GradientConfig;

  // constructor
  constructor(private authGoogleService:AuthGoogleService, private router:Router,private spinner: NgxSpinnerService) {
    this.visibleUserList = false;
    this.chatMessage = false;
    this.spinner.show();
  }

  ngOnInit(){
    setTimeout(() => {
      
    const accesToken = localStorage.getItem('access_token');
    if(!accesToken || accesToken == 'null'){
      this.getTokenBackend();
    }else{
      this.spinner.hide();
    }
    }, 2000);
  }

  getTokenBackend(){
    this.authGoogleService.authUserGoogle().subscribe({
      next: res=> {
        localStorage.setItem('access_token',res['token']);
        if(res['User'] == false){
          sessionStorage.setItem('update_true','true');
          this.router.navigate(['/updateDatos'])
        }
        this.spinner.hide();
      },
      error:error => {
        this.logout();
        console.log(error)
      }
    })
  }

  showData(){
    let data = JSON.stringify(this.authGoogleService.getProfile());
    console.log(data);
  }

  logout(){
    localStorage.clear();
    this.authGoogleService.logout();
    this.router.navigate(['/'])
  }

  // public method
  onChatToggle(friendID: number) {
    this.friendId = friendID;
    this.chatMessage = !this.chatMessage;
  }

  ngDoCheck() {
    if (document.querySelector('body')?.classList.contains('elite-rtl')) {
      this.gradientConfig.isRtlLayout = true;
    } else {
      this.gradientConfig.isRtlLayout = false;
    }
  }
}
