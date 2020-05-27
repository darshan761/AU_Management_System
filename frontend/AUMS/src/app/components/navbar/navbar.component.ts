import { Component, OnInit } from '@angular/core';
import { SocialLoginModule, AuthServiceConfig, AuthService } from 'angular-6-social-login';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public OAuth: AuthService, private router: Router) { }

  ngOnInit() {
  }

  logout() {
    alert("Log Out");
    sessionStorage.clear();
    this.router.navigate([`/login`]);
    // this.OAuth.signOut().then(data =>{
    //   sessionStorage.setItem('userId', undefined);
    //   this.router.navigate([`/login`]);
    // });
   }

}
