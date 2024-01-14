import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const updatedatosGuard: CanActivateFn = (route, state) => {
  const rutas = inject(Router);
  let inicio = localStorage.getItem('sesionLoginInicio');
  if(!inicio){
    rutas.navigate(["/login"]);
    return false;
    
  }else if(inicio != "super_administrador"){
    
    return false;
  }
  
  return true;
};
