import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Course } from 'src/app/models/Course';


@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private GET_ALL_COURSES = '/api/course/';
  constructor(private http: HttpClient) { }

  getAllCourse(){
    return this.http.get(this.GET_ALL_COURSES);
  }

  getCourseById(id) {
   return this.http.get(this.GET_ALL_COURSES + id);
   }
}
