import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsignacionAprobadaComponent } from './consignacion-aprobada.component';

describe('ConsignacionAprobadaComponent', () => {
  let component: ConsignacionAprobadaComponent;
  let fixture: ComponentFixture<ConsignacionAprobadaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsignacionAprobadaComponent]
    });
    fixture = TestBed.createComponent(ConsignacionAprobadaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
