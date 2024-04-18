import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BadwordComponent } from './badword.component';

describe('BadwordComponent', () => {
  let component: BadwordComponent;
  let fixture: ComponentFixture<BadwordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BadwordComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BadwordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
