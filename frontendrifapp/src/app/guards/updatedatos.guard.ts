import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const updatedatosGuard: CanActivateFn = (route, state) => {
  const rutas = inject(Router);
  debugger
  let updateDatos = localStorage.getItem('updateDatos');
  if(!updateDatos){
    return rutas.navigate(['/']);
    // return false
  }
  
  return true;
};
