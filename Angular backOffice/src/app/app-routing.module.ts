import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ConversationComponent } from './views/conversation/conversation.component'
import { ForumComponent } from './views/forum/forum.component'

import { DefaultLayoutComponent } from './containers';
import { Page404Component } from './views/pages/page404/page404.component';
import { Page500Component } from './views/pages/page500/page500.component';
import { LoginComponent } from './views/pages/login/login.component';
import { RegisterComponent } from './views/pages/register/register.component';
import { CourseComponent } from './views/course/course.component';
import { ClassroomComponent } from './views/classroom/classroom.component';
import { ChapterComponent } from './views/chapter/chapter.component';
import { ReclamationComponent } from './views/reclamation/reclamation.component';
import { UserComponent } from './views/user/user.component';
import { ClassroomDetailsComponent  } from './views/classroom-details/classroom-details.component';
import { CourseDetailsComponent  } from './views/course-details/course-details.component';
import {UserUpdateComponent} from "./views/user-update/user-update.component";
import {DepartmentsComponent} from "./views/departments/departments.component";
import {DepartmentsUpdateComponent} from "./views/departments-update/departments-update.component";
import {RecUpdateComponent} from "./views/rec-update/rec-update.component";
import { AffectTeachersComponent } from '../app/views/affect-teachers/affect-teachers.component';
import { ChatComponent } from './chat/chat.component';


import {MainComponent} from './chat/main/main.component';
import { UserChatComponent } from './chat/user-chat/user-chat.component';
import {UserProfileComponent} from "./views/user-profile/user-profile.component";

import {ProfileComponent} from "./views/profile/profile.component";


import { EditQuestionComponent } from './views/forum/edit-question/edit-question.component';
import { StatComponent } from './views/forum/stat/stat.component';
import { AjoutQuestionComponent } from './views/forum/ajout-question/ajout-question.component';


const routes: Routes = [
  { path: 'front-office', loadChildren: () => import('./containers/user-layout/user-layout.module').then(m => m.UserLayoutModule) },

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'dashboard',
        loadChildren: () =>
          import('./views/dashboard/dashboard.module').then((m) => m.DashboardModule)
      },
      {
        path: 'user',
        component:UserComponent ,
      },
      {
        path: 'conversation',
        component:ConversationComponent ,
      },
      {
        path: 'forum',
        component:ForumComponent ,
      },
      {path: 'chat/:userId', component: ChatComponent}
      ,
      {
        path: 'Classroom/all',
        component:ClassroomComponent ,
      },
      {
        path: 'rec-update/:id',
        component: RecUpdateComponent,
      },
      {
        path: 'course',
        component:CourseComponent ,
      },
      {
        path: 'user-update/:id',
        component: UserUpdateComponent,
      },
      {
        path: 'profile',
        component: UserProfileComponent,
      },
      {
        path: 'department-update/:id',
        component: DepartmentsUpdateComponent
      },
      {
        path: 'chapter/:id',
        component:ChapterComponent ,
      },
      {
        path: 'reclamations',
        component:ReclamationComponent ,
      },
      {
        path: 'departments',
        component:DepartmentsComponent ,
      },
      {
        path: 'profile/:id',
        component: ProfileComponent
      },
      {
        path: 'Classroom/Details/:id',
        component: ClassroomDetailsComponent,
      },
      {
        path: 'Course/Details/:id',
        component: CourseDetailsComponent,
      },
      {
        path: 'AffectTeachers/:id',
        component: AffectTeachersComponent,
      },
      {
        path: 'GPT',
        component: MainComponent,
      },
      {
        path: 'GPT/user',
        component: UserChatComponent,
      },
      {
        path: 'GPT/chat',
        component: ChatComponent,
      },
      {
        path: 'forum',
        component:ForumComponent ,
      },
      {
        path: 'Question-Details/:id', 
        component: EditQuestionComponent, 
      },
      {
        path: 'stat', 
        component: StatComponent, 
      },
      {
        path: 'ajoutquestion', 
        component: AjoutQuestionComponent, 
      },

      {
        path: 'theme',
        loadChildren: () =>
          import('./views/theme/theme.module').then((m) => m.ThemeModule)
      },
      {
        path: 'base',
        loadChildren: () =>
          import('./views/base/base.module').then((m) => m.BaseModule)
      },
      {
        path: 'buttons',
        loadChildren: () =>
          import('./views/buttons/buttons.module').then((m) => m.ButtonsModule)
      },
      {
        path: 'forms',
        loadChildren: () =>
          import('./views/forms/forms.module').then((m) => m.CoreUIFormsModule)
      },
      {
        path: 'charts',
        loadChildren: () =>
          import('./views/charts/charts.module').then((m) => m.ChartsModule)
      },
      {
        path: 'icons',
        loadChildren: () =>
          import('./views/icons/icons.module').then((m) => m.IconsModule)
      },
      {
        path: 'notifications',
        loadChildren: () =>
          import('./views/notifications/notifications.module').then((m) => m.NotificationsModule)
      },
      {
        path: 'widgets',
        loadChildren: () =>
          import('./views/widgets/widgets.module').then((m) => m.WidgetsModule)
      },
      {
        path: 'pages',
        loadChildren: () =>
          import('./views/pages/pages.module').then((m) => m.PagesModule)
      },
    ]
  },

  {
    path: '404',
    component: Page404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: Page500Component,
    data: {
      title: 'Page 500'
    }
  },

  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  {path: '**', redirectTo: 'dashboard'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      scrollPositionRestoration: 'top',
      anchorScrolling: 'enabled',
      initialNavigation: 'enabledBlocking'
      // relativeLinkResolution: 'legacy'
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
