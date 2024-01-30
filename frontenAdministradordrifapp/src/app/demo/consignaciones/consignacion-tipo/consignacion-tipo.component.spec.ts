import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsignacionTipoComponent } from './consignacion-tipo.component';

describe('ConsignacionTipoComponent', () => {
  let component: ConsignacionTipoComponent;
  let fixture: ComponentFixture<ConsignacionTipoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsignacionTipoComponent]
    });
    fixture = TestBed.createComponent(ConsignacionTipoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
