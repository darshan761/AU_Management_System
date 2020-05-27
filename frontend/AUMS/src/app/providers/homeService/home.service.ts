import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private apiUrl = "http://localhost:8080/login";

  constructor() { }
}
