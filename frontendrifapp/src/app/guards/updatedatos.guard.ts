import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const updatedatosGuard: CanActivateFn = (route, state) => {
  const rutas = inject(Router);
  
  let updateDatos = sessionStorage.getItem('update_true');
  if(!updateDatos){
     return rutas.navigate(['/page404']);
    // return false
  }
  
  return true;
};
