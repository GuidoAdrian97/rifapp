// Angular Import
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// project import
import { AdminComponent } from './theme/layout/admin/admin.component';
import { GuestComponent } from './theme/layout/guest/guest.component';
import { UpdateDatosComponent } from './demo/authentication/update-datos/update-datos.component';
import { updatedatosGuard } from './guards/updatedatos.guard';
import { notfounGuard } from './guards/notfoun.guard';
import { ErrorpruebaComponent } from './pageError/errorprueba/errorprueba.component';
import { Page500Component } from './pageError/page500/page500.component';
import { Page404Component } from './pageError/page404/page404.component';
import { UserProfileComponent } from './demo/user-profile/user-profile.component';
import { Page403Component } from './pageError/page403/page403.component';
import { CreateRifasComponent } from './demo/create-rifas/create-rifas.component';
 
const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    // canActivate:[notfounGuard],
    children: [
      {
        path: '',
        redirectTo: '/auth/signin',
        pathMatch: 'full'
      },
      {
        path: 'Inicio',
        loadComponent: () => import('./demo/dashboard/dash-analytics/dash-analytics.component')
      },
      {
        path: 'component',
        loadChildren: () => import('./demo/ui-element/ui-basic.module').then((m) => m.UiBasicModule)
      },
      {
        path: 'chart',
        loadComponent: () => import('./demo/chart & map/core-apex/core-apex.component')
      },
      {
        path: 'forms',
        loadComponent: () => import('./demo/forms & tables/form-elements/form-elements.component')
      },
      {
        path: 'tables',
        loadComponent: () => import('./demo/forms & tables/tbl-bootstrap/tbl-bootstrap.component')
      },
      {
        path: 'sample-page',
        loadComponent: () => import('./demo/sample-page/sample-page.component')
      },
      { 
        path: 'userProfile', component: UserProfileComponent
      },
      { 
        path: 'CreateRifa', component: CreateRifasComponent
      },
    ]
  },
  {
    path: '',
    component: GuestComponent,
    children: [
      {
        path: 'auth/signup/:{user}',
        loadComponent: () => import('./demo/authentication/sign-up/sign-up.component')
      },
      {
        path: 'auth/signup',
        loadComponent: () => import('./demo/authentication/sign-up/sign-up.component')
      },
      {
        path: 'auth/signin',
        loadComponent: () => import('./demo/authentication/sign-in/sign-in.component')
      }
    
    ]
  },
  { 
    path: 'updateDatos', component: UpdateDatosComponent,canActivate:[updatedatosGuard]
  },
  { 
    path: 'pruebaerror', component: ErrorpruebaComponent
  },
  { 
    path: 'page500', component: Page500Component
  },
  { 
    path: 'page404', component: Page404Component
  },
  { 
    path: 'page403', component: Page403Component
  },
  
  
  
  // { path: '**', redirectTo: '/' } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
