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
import { CourseComponent } from '../app/views/course/course.component';
import { ChapterComponent } from '../app/views/chapter/chapter.component';
import { UserComponent } from '../app/views/user/user.component';
import { PaginationsComponent } from '../app/views/base/paginations/paginations.component';







const APP_CONTAINERS = [
  DefaultFooterComponent,
  DefaultHeaderComponent,
  DefaultLayoutComponent
];

@NgModule({
  declarations: [
    UserComponent,
    ConversationComponent,
    AppComponent,
    APP_CONTAINERS,
    ClassroomComponent,
    CourseComponent,
    ChapterComponent,
    ReclamationComponent

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
    HttpClientModule 
  ],
  providers: [
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    },
    IconSetService,
    Title
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [ 
    
    ReclamationComponent,   
    ConversationComponent,
    AppComponent,
    APP_CONTAINERS,
    ClassroomComponent,
    UserComponent
] 
})
export class AppModule {
}
