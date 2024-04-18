import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForumFrontComponent } from './forum-front.component';

describe('ForumFrontComponent', () => {
  let component: ForumFrontComponent;
  let fixture: ComponentFixture<ForumFrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ForumFrontComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ForumFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
