import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatIconModule, MatSelectModule, MatSortModule, MatAutocompleteModule, MatFormFieldModule, MatInputModule, MatTableModule, MatPaginatorModule, MatCardModule, MatToolbarModule, MatChipsModule, MatDialogModule, MatSnackBarModule, MatExpansionModule, MatGridListModule } from '@angular/material';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { ChartsModule } from 'ng2-charts';
import { LoginService } from 'src/app/providers/loginService/login.service';
import { of } from 'rxjs';
import { HomeComponent } from '../home/home.component';
import { CourseDetailsComponent } from '../course-details/course-details.component';
import { TrainingComponent } from '../training/training.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { InstructorComponent } from '../instructor/instructor.component';
import { CoursesComponent } from '../courses/courses.component';
import { ManageCourseComponent } from '../manage-course/manage-course.component';
import { TrainingDetailsComponent } from '../training-details/training-details.component';
import { AddCourseComponent } from '../add-course/add-course.component';
import { PageNotFoundComponent } from '../page-not-found/page-not-found.component';
import { DragDropDirective } from 'src/app/drag-drop.directive';
import { TrainingVersionDetailsComponent } from '../training-version-details/training-version-details.component';
import { TrainingMaterialComponent } from '../training-material/training-material.component';
import { AuthService, SocialUser } from 'angular-6-social-login';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Users } from 'src/app/models/Users';
import { ActivatedRouteSnapshot } from '@angular/router';


class MockLoginService {
  Savesresponse() {
    return of([
      {
      "courseId": 1,
      "courseDescription": "Front End Library",
      "courseLocation": "Mumbai",
      "courseName": "React Native",
      "coursePrerequisites": "JavaScript",
      "courseSkills": "FrontEnd",
      "courseAdminId": 1
      }]);
  }
}


class MockAuthService {
  signIn() {
    return of(
      {
        email: "darshan.sudhirpatil@accoliteindia.com",
        id: "101415644573374848357",
        idToken: "eyJhbGciOiJSUzI1NiIsImtpZCI6IjQ5MjcxMGE3ZmNkYjE1Mzk2MGNlMDFmNzYwNTIwYTMyYzg0NTVkZmYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiYXpwIjoiNjgyMjA5MzgwNTI2LTVoNWk4ZTQzc3VlNzc0ZWhybXY1MmV2cnMwbXJrbWJkLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiNjgyMjA5MzgwNTI2LTVoNWk4ZTQzc3VlNzc0ZWhybXY1MmV2cnMwbXJrbWJkLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTAxNDE1NjQ0NTczMzc0ODQ4MzU3IiwiaGQiOiJhY2NvbGl0ZWluZGlhLmNvbSIsImVtYWlsIjoiZGFyc2hhbi5zdWRoaXJwYXRpbEBhY2NvbGl0ZWluZGlhLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoiVkxYMzFsY0UtdVhkU1BrODFHZFhWUSIsIm5hbWUiOiJEYXJzaGFuIFBhdGlsIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hLS9BT2gxNEdqZTdSNVQ1cDJCWTEtdEI1N3NCb1l3V0RYckJaQzBfZm1zZmN3UD1zOTYtYyIsImdpdmVuX25hbWUiOiJEYXJzaGFuIiwiZmFtaWx5X25hbWUiOiJQYXRpbCIsImxvY2FsZSI6ImVuIiwiaWF0IjoxNTkxMzY5NzQwLCJleHAiOjE1OTEzNzMzNDAsImp0aSI6ImZiNGRmZmM0MjRjOWI1Nzc4ZTdjY2NjMjdjMzgyYzllYzc5NWI1NjQifQ.Qu0Uob87xOCFhQXmleKOY0qTyyxo3BAjr8Aw3N1K3MJjAWmo0OiILQO45sKPCwqINu5G6-VPKSJhGAz8_oY7Q_Lh_2f2BM6IgaJKaOhHHdTuOg4L-rvv5ehTcZ6MOqkLxRTiSm5G-r22ncsQM-u8eK05d7mV4CVQmnbGlHOejBidarP4IBfw-aj6h-tBSq8FHLToM7_wJQlmEiQNJKhXuSgKZE34yL_aGuzCJqsVHV12OkMnm4AKKm9IpIuzxnHwFTaymeoCrtJmFT_dhMLgz4MpZcGnwOYVWW98hSyCm7HOl9N52zURPSNv14VEE3nEahz6oTL5q3AkjbcYwoZkmg",
        image: "https://lh3.googleusercontent.com/a-/AOh14Gje7R5T5p2BY1-tB57sBoYwWDXrBZC0_fmsfcwP=s96-c",
        name: "Darshan Patil",
        provider: "google",
        token: "ya29.a0AfH6SMCpLzU0i4SysnF_QY7_nHMnN0FoosTth5qVQXuauV1nf-W0GQTYfgz79H579qy2vgK8gMAPGPkifAuutHTbgNz03wV9vTNdUu12O_kIWnJfuJ8wwLswnfoJTW6K02X0HiaISzdApbajCzbj2W9w1oT47Yif7eA8"
      }
      );
  }
}



fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let loginService: LoginService;
  let oauth: AuthService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent,
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
        DragDropDirective,
        TrainingVersionDetailsComponent,
        TrainingMaterialComponent
      ],
      imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientTestingModule,
        RouterTestingModule,
        BrowserAnimationsModule,
        MatButtonModule,
        MatIconModule,
        MatSelectModule,
        MatSortModule,
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
        MatFileUploadModule,
        ChartsModule,
        MatGridListModule,
        FormsModule
      ], providers: [{
        provide: LoginService,
        useClass: MockLoginService,
      }, {
        provide: AuthService,
        useClass: MockAuthService,
      }]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    loginService = TestBed.get(LoginService);
    oauth = TestBed.get(AuthService);
    sessionStorage.setItem('userId', '1');
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should check login', () => {
  spyOn(oauth, 'signIn').and.callThrough();
  component.SignIn('google');
  // console.log('dgsvuty'+ component.user);
  expect(oauth.signIn).toHaveBeenCalled();
    });

});
