import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsignacionEsperaComponent } from './consignacion-espera.component';

describe('ConsignacionEsperaComponent', () => {
  let component: ConsignacionEsperaComponent;
  let fixture: ComponentFixture<ConsignacionEsperaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsignacionEsperaComponent]
    });
    fixture = TestBed.createComponent(ConsignacionEsperaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
