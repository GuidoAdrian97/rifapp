import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsignacionRechazadaComponent } from './consignacion-rechazada.component';

describe('ConsignacionRechazadaComponent', () => {
  let component: ConsignacionRechazadaComponent;
  let fixture: ComponentFixture<ConsignacionRechazadaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsignacionRechazadaComponent]
    });
    fixture = TestBed.createComponent(ConsignacionRechazadaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
