import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpcConsignacionComponent } from './opc-consignacion.component';

describe('OpcConsignacionComponent', () => {
  let component: OpcConsignacionComponent;
  let fixture: ComponentFixture<OpcConsignacionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OpcConsignacionComponent]
    });
    fixture = TestBed.createComponent(OpcConsignacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
