import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CourseService } from 'src/app/providers/courseService/course.service';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  months = ['January','February','March','April','May','June','July','August','September','October','November','December'];
  years = ['2020','2019','2018','2017','2016','2015','2014','2013','2012'];
  locations=['Mumbai','Bengaluru','Hyderabad','Delhi','Chennai','Gurugram'];

  courseForm = new FormGroup({
    courseName: new FormControl('', Validators.required),
    courseDesc: new FormControl('', Validators.required),
    courseSkill: new FormControl('', Validators.required),
    coursePrerequisites: new FormControl('', Validators.required),
    courseLocation: new FormControl('', Validators.required),
    courseMonth: new FormControl('', Validators.required),
    courseYear: new FormControl('', Validators.required),
    creatorId: new FormControl('', Validators.required)
  });

  constructor(private snackBar: MatSnackBar, private router: Router, private courseService: CourseService) { }

  ngOnInit() {
    this.courseForm.patchValue({
      creatorId: sessionStorage.getItem('userId')
    });
  }

  addCourse() {
    console.log(this.courseForm.value);
    this.courseService.addCourse(this.courseForm.value);
    this.openSnackBar('Added Successfully', 'Done!');
    this.router.navigate([`/manage`]);
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
