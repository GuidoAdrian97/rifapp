import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRifasComponent } from './create-rifas.component';

describe('CreateRifasComponent', () => {
  let component: CreateRifasComponent;
  let fixture: ComponentFixture<CreateRifasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateRifasComponent]
    });
    fixture = TestBed.createComponent(CreateRifasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
