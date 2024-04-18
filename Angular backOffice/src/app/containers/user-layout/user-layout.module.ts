import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserLayoutRoutingModule } from './user-layout-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { QuestiondetailComponent } from 'src/app/pages/questiondetail/questiondetail.component';
import { ForumFrontComponent } from 'src/app/pages/forum-front/forum-front.component';
import { UserLayoutComponent } from './user-layout.component';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatPaginatorModule } from '@angular/material/paginator';

@NgModule({
  declarations: [QuestiondetailComponent, ForumFrontComponent, UserLayoutComponent],
  imports: [
    CommonModule,
    UserLayoutRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatFormFieldModule,
    MatSelectModule,
    MatPaginatorModule

  ],
  providers: [], // Optionally, add providers if needed
})
export class UserLayoutModule { }
