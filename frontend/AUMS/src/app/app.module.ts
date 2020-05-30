import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { DragDropDirective } from './drag-drop.directive';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { CourseDetailsComponent } from './components/course-details/course-details.component';
import { TrainingComponent } from './components/training/training.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GoogleLoginProvider, AuthService, AuthServiceConfig } from 'angular-6-social-login';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { InstructorComponent } from './components/instructor/instructor.component';
import {  MatSelectModule } from '@angular/material';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { ReactiveFormsModule } from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { CoursesComponent } from './components/courses/courses.component';
import {MatTableModule} from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatChipsModule} from '@angular/material/chips';
import { ManageCourseComponent } from './components/manage-course/manage-course.component';
import { TrainingDetailsComponent } from './components/training-details/training-details.component';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { MatDialogModule, MatDialog, MatDialogRef } from '@angular/material/dialog';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatFileUploadModule } from 'angular-material-fileupload';


export function configs(){
  const config = new AuthServiceConfig(
    [
      {
        id: GoogleLoginProvider.PROVIDER_ID,
        provider: new GoogleLoginProvider('682209380526-5h5i8e43sue774ehrmv52evrs0mrkmbd.apps.googleusercontent.com')
      }
    ]
  );
  return config;
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    CourseDetailsComponent,
    TrainingComponent,
    NavbarComponent,
    InstructorComponent,
    CoursesComponent,
    ManageCourseComponent,
    TrainingDetailsComponent,
    AddCourseComponent,
    PageNotFoundComponent,
    DragDropDirective
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatSelectModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatCardModule,
    MatToolbarModule,
    MatChipsModule,
    MatDialogModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatFileUploadModule
  ],
  providers: [
    AuthService,
    {
      provide: AuthServiceConfig,
      useFactory: configs
    },
    { provide: MatDialogRef, useValue: {} }
  ],
    entryComponents: [
        InstructorComponent,
        TrainingComponent
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
