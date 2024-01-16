import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorpruebaComponent } from './errorprueba.component';

describe('ErrorpruebaComponent', () => {
  let component: ErrorpruebaComponent;
  let fixture: ComponentFixture<ErrorpruebaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ErrorpruebaComponent]
    });
    fixture = TestBed.createComponent(ErrorpruebaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
