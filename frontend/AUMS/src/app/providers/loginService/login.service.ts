import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url;
  constructor(private http: HttpClient) { }

  Savesresponse(response) {
    this.url = "http:localhost:4200/api/login/savesResponse";
    return this.http.post(this.url, response);
  }

}
