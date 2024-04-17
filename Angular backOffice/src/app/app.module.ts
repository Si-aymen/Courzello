import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { BrowserModule, Title } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { NgScrollbarModule } from 'ngx-scrollbar';

// Import routing module
import { AppRoutingModule } from './app-routing.module';

// Import app component
import { ChartsModule } from './views/charts/charts.module';
import { ChartjsModule } from '@coreui/angular-chartjs';
import { WidgetsModule } from './views/widgets/widgets.module';
import { ReclamationComponent } from './views/reclamation/reclamation.component';




import { EditQuestionComponent } from './views/forum/edit-question/edit-question.component';
import { StatComponent } from './views/forum/stat/stat.component';
import { AjoutQuestionComponent } from './views/forum/ajout-question/ajout-question.component';




import { AppComponent } from './app.component';

// Import containers
import { DefaultFooterComponent, DefaultHeaderComponent, DefaultLayoutComponent } from './containers';

import {
  AvatarModule,
  BadgeModule,
  BreadcrumbModule,
  ButtonGroupModule,
  ButtonModule,
  CardModule,
  DropdownModule,
  FooterModule,
  FormModule,
  GridModule,
  HeaderModule,
  ListGroupModule,
  NavModule,
  ProgressModule,
  SharedModule,
  SidebarModule,
  TabsModule,
  UtilitiesModule
} from '@coreui/angular';

import { IconModule, IconSetService } from '@coreui/icons-angular';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ClassroomComponent } from './views/classroom/classroom.component';
import { ConversationComponent } from './views/conversation/conversation.component';
import { ForumComponent } from './views/forum/forum.component';
import { ToastrModule } from 'ngx-toastr';
import { CourseComponent } from '../app/views/course/course.component';
import { ChapterComponent } from '../app/views/chapter/chapter.component';
import { UserComponent } from '../app/views/user/user.component';
import { ClassroomDetailsComponent  } from './views/classroom-details/classroom-details.component';
import { CourseDetailsComponent  } from './views/course-details/course-details.component';
import {UserUpdateComponent} from './views/user-update/user-update.component';
import {LoginComponent} from "./views/pages/login/login.component";
import {DepartmentsComponent} from "./views/departments/departments.component";
import {DepartmentsUpdateComponent} from "./views/departments-update/departments-update.component";
import {RecUpdateComponent} from "./views/rec-update/rec-update.component";

import { AffectTeachersComponent } from '../app/views/affect-teachers/affect-teachers.component';


import { ChatComponent } from './chat/chat.component';

import {UserChatComponent} from './chat/user-chat/user-chat.component';
import {MainComponent} from './chat/main/main.component';
import {RegisterComponent} from "./views/pages/register/register.component";
import {UserProfileComponent} from "./views/user-profile/user-profile.component";
import {ProfileComponent} from "./views/profile/profile.component";



import { GlossaryComponent } from './views/glossary/glossary.component';





const APP_CONTAINERS = [
  DefaultFooterComponent,
  DefaultHeaderComponent,
  DefaultLayoutComponent
];

@NgModule({
  declarations: [
    ChatComponent,
    LoginComponent,
    DepartmentsComponent,
    UserComponent,
    LoginComponent,
    DepartmentsComponent,
    UserComponent,
    RegisterComponent,
    ConversationComponent,
    AppComponent,
    APP_CONTAINERS,
    ClassroomComponent,
    CourseComponent,
    ChapterComponent,
    ReclamationComponent,
    ClassroomDetailsComponent,
    CourseDetailsComponent,
    UserUpdateComponent,
    DepartmentsUpdateComponent,
    RecUpdateComponent,

    AffectTeachersComponent,
    UserProfileComponent,
    AffectTeachersComponent,
    UserProfileComponent,
    ProfileComponent,
    DepartmentsUpdateComponent,
    RecUpdateComponent,
    UserChatComponent,
    ChatComponent,
    EditQuestionComponent,
    StatComponent,
    AjoutQuestionComponent,
    ForumComponent,
    GlossaryComponent,
    
    MainComponent


  ],
  imports: [
    WidgetsModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AvatarModule,
    BreadcrumbModule,
    FooterModule,
    DropdownModule,
    GridModule,
    HeaderModule,
    SidebarModule,
    IconModule,
    NavModule,
    ButtonModule,
    FormModule,
    UtilitiesModule,
    ButtonGroupModule,
    ReactiveFormsModule,
    SidebarModule,
    SharedModule,
    TabsModule,
    ListGroupModule,
    ProgressModule,
    BadgeModule,
    ListGroupModule,
    CardModule,
    NgScrollbarModule,
    FormsModule,
    ChartsModule,
    ChartjsModule,
    HttpClientModule,
    ToastrModule.forRoot()

  ],
  providers: [

    IconSetService,
    Title
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [
    LoginComponent,
    UserUpdateComponent,
    UserProfileComponent,
    ProfileComponent,
    ReclamationComponent,
    ConversationComponent,
    AppComponent,
    APP_CONTAINERS,
    ClassroomComponent,
    UserComponent,
    RegisterComponent,
    ClassroomDetailsComponent,
    CourseDetailsComponent,
    DepartmentsComponent,
    DepartmentsUpdateComponent,
    EditQuestionComponent,
    StatComponent,
    AjoutQuestionComponent,
    GlossaryComponent,
    RecUpdateComponent
  ]
})
export class AppModule {
}
