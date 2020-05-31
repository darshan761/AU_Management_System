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

  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  
  public barChartLabels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  public barChartType = 'bar';
  public barChartLegend = true;
  public barChartData = [
    {data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A'},
    {data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B'}
  ];

  public pieChartLabels = ['Sales Q1', 'Sales Q2', 'Sales Q3', 'Sales Q4'];
  public pieChartData = [120, 150, 180, 90];
  public pieChartType = 'pie';
  public doughnutChartType = 'doughnut';

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
