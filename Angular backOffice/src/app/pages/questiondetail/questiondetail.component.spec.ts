import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestiondetailComponent } from './questiondetail.component';

describe('QuestiondetailComponent', () => {
  let component: QuestiondetailComponent;
  let fixture: ComponentFixture<QuestiondetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuestiondetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(QuestiondetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
