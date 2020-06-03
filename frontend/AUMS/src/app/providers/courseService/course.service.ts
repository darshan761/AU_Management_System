import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Course } from 'src/app/models/Course';
import { catchError, retry } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  userId = +sessionStorage.getItem('userId');
  headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  private ROOT_COURSE = '/api/course/';
  private GET_COURSE_BY_CREATOR = this.ROOT_COURSE + 'creator/' + this.userId;
  private GET_COURSE_BY_INSTRUCTOR = this.ROOT_COURSE + 'instructor/' + this.userId;

  // userId = sessionStorage.getItem('userId');
  constructor(private router:Router, private http: HttpClient) { }

  getAllCourse() {
    return this.http.get(this.ROOT_COURSE);
  }

  getCourseById(id) {
   return this.http.get(this.ROOT_COURSE + id);
  }

  addCourse(course) {
    this.http.post(this.ROOT_COURSE + 'add/', course).subscribe(data=>{
      console.log('data');
      this.router.navigate([`/manage`]);
    });
    console.log(course);
  }

  getCourseByCreatorId() {
    return this.http.get(this.GET_COURSE_BY_CREATOR);
  }

  getCourseByInstructorId() {
      return this.http.get(this.GET_COURSE_BY_INSTRUCTOR);
  }

  getCourseCount() {
    return this.http.get(this.ROOT_COURSE+'/count');
  }

  getVersionData(courseId) {
    return this.http.get(this.ROOT_COURSE+'/version/'+courseId);
  }

  updateCourse(course) {
    this.http.post(this.ROOT_COURSE + '/save', course).subscribe(data =>{
      console.log(data);
    });
  }

  deleteCourse(courseId) {
    this.http.delete(this.ROOT_COURSE +  '/delete/' + courseId).subscribe();
  }

  handleError(error: HttpErrorResponse){
    console.log('lalalalalalalala', error.message);
    return throwError(error);
    }
}
