import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForumFrontComponent } from 'src/app/pages/forum-front/forum-front.component';
import { QuestiondetailComponent } from 'src/app/pages/questiondetail/questiondetail.component';
import { UserLayoutComponent } from './user-layout.component';

const routes: Routes = [
  { path: '', component: UserLayoutComponent, children: [
    { path: 'forum-v', component: ForumFrontComponent },
    { path: 'detail-question/:id', component: QuestiondetailComponent }
    // Add more child routes for other user-related components (P)
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserLayoutRoutingModule { }
