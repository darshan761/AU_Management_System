import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { ApiResponse } from 'src/app/models/ApiResponse';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private GET_USER = '/api/user/';
  user: any;

  constructor(private http: HttpClient) { }

   Savesresponse(response) {
    this.http.get(this.GET_USER + response.email).subscribe((response: ApiResponse)=>{
    this.user = response.data;
    sessionStorage.setItem('userId', this.user.userId);
    console.log(sessionStorage.getItem('userId'));
    });
  }

}
