import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private GET_USER = '/api/user/';
  user: any;

  constructor(private http: HttpClient) { }

  async Savesresponse(response) {
    await this.http.get(this.GET_USER + response.email).subscribe((data: any[])=>{
    this.user = data;
    sessionStorage.setItem('userId', this.user.userId);
    });
  }

}
