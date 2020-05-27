import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Users } from 'src/app/models/Users';
import { SocialLoginModule, AuthServiceConfig, AuthService } from 'angular-6-social-login';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  data: Users;
  options = ['react', 'angular'];

  ngOnInit() {
    this.data = new Users();
    this.data.email = sessionStorage.getItem('userEmail');
    this.data.name = sessionStorage.getItem('userName');
  }

  constructor(public OAuth: AuthService, private router: Router) { }

  logout() {
   alert(1);
   this.OAuth.signOut().then(data =>{
    this.router.navigate([`/login`]);
   });
  }
}
