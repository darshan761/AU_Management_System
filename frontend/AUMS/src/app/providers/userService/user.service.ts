import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError, retry } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { TouchSequence } from 'selenium-webdriver';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private ROOT_COURSE = '/api/user/';
  constructor(private http: HttpClient) { }

   getAllUsers() {
    return this.http.get(this.ROOT_COURSE);
  }

  getInstructorByCourse(id) {
    console.log('effrf', id);
    return this.http.get('api/instructor/course/' + id);
  }

  addInstructor(training){
    this.http.post('api/training/add', training).subscribe(data =>{
      console.log(data);
    });
  }

  deleteInstructor(trainingId) {
    this.http.delete('api/training/delete/' + trainingId).subscribe((data)=>{
      console.log(data);
    });
  }

  addTrainingMaterial(fileList, courseId, instructorId){
    const formData: FormData = new FormData();
    for(let i of fileList){
      formData.append('file[]', i);
    }

    formData.append('courseId', courseId);
    formData.append('instructorId', instructorId);
    console.log(fileList, courseId, instructorId);
    let headers =  {headers: new  HttpHeaders({ enctype:"multipart/form-data"})};

    this.http.post('api/training/material/add',formData, headers).subscribe();
    console.log('hola!', formData.get('courseId'));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
}
