import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Users } from 'src/app/models/Users';
import { SocialLoginModule, AuthServiceConfig, AuthService } from 'angular-6-social-login';
import { Router } from '@angular/router';
import { CourseService } from 'src/app/providers/courseService/course.service';
import { ApiResponse } from 'src/app/models/ApiResponse';
import { Course } from 'src/app/models/Course';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  data: Users;
  count;
  CourseList: Course[] = [];
  location = ['Mumbai', 'Bengaluru', 'Delhi', 'Hyderabad', 'Chennai'];
  year = ['2014', '2015', '2016', '2017', '2018', '2019', '2020'];
  locationCount: number[] = [0, 0, 0, 0, 0];
  yearCount: number[] = [0, 0, 0, 0, 0, 0, 0];

  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
  };

  public barChartLabels = this.year;
  public barChartType = 'bar';
  public barChartLegend = true;
  public barChartData = [
    {data: [65, 59, 80, 81, 56, 55, 40], label: 'Courses'}
  ];

  public pieChartLabels = this.location;
  public pieChartData = [120, 150, 180, 90, 120];
  public pieChartType = 'pie';
  public doughnutChartType = 'doughnut';

  ngOnInit() {
    this.data = new Users();
    this.data.email = sessionStorage.getItem('userEmail');
    this.data.name = sessionStorage.getItem('userName');
    this.getCourseCount();
    this.getAllCourses();
  }

  constructor(private courseService: CourseService, public OAuth: AuthService, private router: Router) { }

  logout() {
   alert(1);
   this.OAuth.signOut().then(data =>{
    this.router.navigate([`/login`]);
   });
  }

  getCourseCount() {
    return this.courseService.getCourseCount().subscribe((response: ApiResponse)=>{
      this.count = response.data;
    });
  }

  getAllCourses() {
    this.courseService.getAllCourse().subscribe((response: ApiResponse) => {
      console.log(response);
      this.CourseList = response.data;
      this.getLocationStats();
      console.log(this.CourseList);
    });
  }

  getLocationStats() {
    for(let course of this.CourseList){
     if(this.location.includes(course.courseLocation)){
       let index = this.location.indexOf(course.courseLocation);
       this.locationCount[index] += 1;
     }
     console.log(course.courseYear);
     if(this.year.includes(course.courseYear)){
      let index = this.year.indexOf(course.courseYear);
      this.yearCount[index] += 1;
     }
    }
    this.pieChartData = this.locationCount;
    this.barChartData[0].data = this.yearCount;
    console.log('y u no print', this.yearCount, this.year);
  }

}
