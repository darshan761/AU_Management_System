import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { CourseDetailsComponent } from './components/course-details/course-details.component';
import { HomeComponent } from './components/home/home.component';
import { TrainingComponent } from './components/training/training.component';
import { CoursesComponent } from './components/courses/courses.component';
import { ManageCourseComponent } from './components/manage-course/manage-course.component';
import { TrainingDetailsComponent } from './components/training-details/training-details.component';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent},
  { path: 'courses', component: CoursesComponent},
  { path: 'manage', component: ManageCourseComponent},
  { path: 'course/:id', component: CourseDetailsComponent },
  { path: 'cours/add', component: AddCourseComponent},
  { path: 'training', component: TrainingDetailsComponent},
  { path: '**', component: PageNotFoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
