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

  constructor(private http: HttpClient) { }

  ngOnInit() {
    // const url = "http://localhost:4200/api/user";
    // this.http.get(url).subscribe((res)=>{
    //   this.data = res
    //   console.log(this.data)
    // })
    this.data = new Users();
    this.data.id =  localStorage.getItem('userId');
    this.data.email = localStorage.getItem('userEmail');
    this.data.name = localStorage.getItem('userName');
    console.log(this.data);
  }

  // users = new Users();  
  // constructor(public OAuth: AuthService,    private router: Router) { }  
  // ngOnInit() {  
  //   this.users = JSON.parse(localStorage.getItem('users'));  
  //   console.log(this.users.id);  
  // }  
  // logout() {  
  //  alert(1);  
  //   this.OAuth.signOut().then(data => {  
  //     debugger;  
  //     // this.router.navigate([`/login`]);  
  //   });  
  // }  


}
