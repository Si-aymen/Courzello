import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AffectTeachersComponent } from './affect-teachers.component';

describe('AffectTeachersComponent', () => {
  let component: AffectTeachersComponent;
  let fixture: ComponentFixture<AffectTeachersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AffectTeachersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AffectTeachersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
