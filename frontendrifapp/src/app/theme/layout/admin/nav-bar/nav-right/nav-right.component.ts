// Angular Import
import { Component, DoCheck, OnInit } from '@angular/core';
import { animate, style, transition, trigger } from '@angular/animations';
import { GradientConfig } from 'src/app/app-config';

// bootstrap
import { NgbDropdownConfig } from '@ng-bootstrap/ng-bootstrap';
import { AuthGoogleService } from 'src/app/services/auth-google.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-right',
  templateUrl: './nav-right.component.html',
  styleUrls: ['./nav-right.component.scss'],
  providers: [NgbDropdownConfig],
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
  constructor(private authGoogleService:AuthGoogleService, private router:Router) {
    this.visibleUserList = false;
    this.chatMessage = false;
  }

  ngOnInit(){
    setTimeout(() => {
    const accesToken = localStorage.getItem('access_token');
    if(accesToken == null){
      this.getTokenBackend();
    }
    }, 1500);
  }

  getTokenBackend(){
    this.authGoogleService.authUserGoogle().subscribe({
      next: res=> {
        localStorage.setItem('access_token',res['token'])
        console.log(`OK`,res)
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
