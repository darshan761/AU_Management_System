import { Component, OnInit } from '@angular/core';
import { AuthService, GoogleLoginProvider } from 'angular-6-social-login';
import { LoginService } from 'src/app/providers/loginService/login.service';
import { Router } from '@angular/router';
import { Users } from 'src/app/models/Users';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  response;
  googleLogoURL = 'https://raw.githubusercontent.com/fireflysemantics/logo/master/Google.svg';
  users = new Users();

  constructor(
    public OAuth: AuthService,
    private loginService: LoginService,
    private router: Router,
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
  ) { this.matIconRegistry.addSvgIcon(
    'logo',
    this.domSanitizer.bypassSecurityTrustResourceUrl(this.googleLogoURL)); }

  ngOnInit() {
  }

  public SignIn(provider: string) {
    let platformProvider;
    if(provider == 'google') {
      platformProvider = GoogleLoginProvider.PROVIDER_ID;
    }

    this.OAuth.signIn(platformProvider).then(users => {
      console.log(users);
      this.users = users;

      localStorage.setItem('userId', this.users.id);
      localStorage.setItem('userEmail', this.users.email);
      localStorage.setItem('userName', this.users.name);
      // console.log(localStorage.getItem('users'));
      this.router.navigate([`/home`]);
      // this.Savesresponse(users);
    })
  }

  Savesresponse(users: Users) {
    this.loginService.Savesresponse(users).subscribe((res: any) => {
      debugger;  
      console.log(res);
      this.users = res;
      this.response = res.userDetail;
      localStorage.setItem('users', JSON.stringify( this.users));  
      console.log(localStorage.setItem('users', JSON.stringify(this.users)));  
      this.router.navigate([`/home`]);  
    })  
  }  

}
