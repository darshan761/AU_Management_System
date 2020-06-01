import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  userId = sessionStorage.getItem('userId');

  constructor(private http: HttpClient) { }

  getTrainingByInstructor(courseId) {
    console.log(courseId, this.userId);
    return this.http.get('api/training/material/'+courseId+'/'+this.userId );
  }

  deleteTraining(fileId) {
    this.http.delete('api/training/material/delete/'+ fileId ).subscribe();
  }

  sendMail(email) {
    return this.http.post('api/training/material/mail', email);
  }
}
