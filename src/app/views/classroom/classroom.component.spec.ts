import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ClassroomComponent } from './classroom.component';
import { ButtonModule, CardModule, FormModule, GridModule } from '@coreui/angular';
import { IconSetService } from '@coreui/icons-angular';
import { RouterTestingModule } from '@angular/router/testing';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { DocsComponentsModule } from '../../../components';

describe('ClassroomComponent', () => {
  let component: ClassroomComponent;
  let fixture: ComponentFixture<ClassroomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClassroomComponent], // Add your component to the declarations
      imports: [CardModule, GridModule, DocsComponentsModule, RouterTestingModule],
      providers: [IconSetService],
      schemas: [CUSTOM_ELEMENTS_SCHEMA], // Add this line to handle custom elements
    }).compileComponents();

    fixture = TestBed.createComponent(ClassroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
