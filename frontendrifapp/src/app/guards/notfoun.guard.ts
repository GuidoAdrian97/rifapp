import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const notfounGuard: CanActivateFn = (route, state) => {
  let rout = inject(Router)

  return rout.navigate(['/']);
};
