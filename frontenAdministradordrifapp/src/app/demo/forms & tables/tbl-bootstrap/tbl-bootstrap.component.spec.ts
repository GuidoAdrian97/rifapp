import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TblBootstrapComponent } from './tbl-bootstrap.component';

describe('TblBootstrapComponent', () => {
  let component: TblBootstrapComponent;
  let fixture: ComponentFixture<TblBootstrapComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [TblBootstrapComponent]
    });
    fixture = TestBed.createComponent(TblBootstrapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
