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
  userfromEmail: any;

  constructor(
    public OAuth: AuthService,
    private loginService: LoginService,
    private router: Router,
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
  ) { this.matIconRegistry.addSvgIcon('logo', this.domSanitizer.bypassSecurityTrustResourceUrl(this.googleLogoURL)); }

  ngOnInit() {
    if(sessionStorage.getItem('userId')){
      this.router.navigate([`/home`]);
    }
  }

  public async SignIn(provider: string) {
    let platformProvider;
    if(provider === 'google') {
      platformProvider = GoogleLoginProvider.PROVIDER_ID;
    }

    this.OAuth.signIn(platformProvider).then(users => {
      console.log(users);
      this.loginService.Savesresponse(users);
      sessionStorage.setItem('userEmail', users.email);
      sessionStorage.setItem('userName', users.name);
      sessionStorage.setItem('userImg', users.image);
      this.router.navigate([`/home`]);
    });
  }
}
