import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { updatedatosGuard } from './updatedatos.guard';

describe('updatedatosGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => updatedatosGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
