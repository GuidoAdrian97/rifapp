import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDatosComponent } from './update-datos.component';

describe('UpdateDatosComponent', () => {
  let component: UpdateDatosComponent;
  let fixture: ComponentFixture<UpdateDatosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateDatosComponent]
    });
    fixture = TestBed.createComponent(UpdateDatosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
