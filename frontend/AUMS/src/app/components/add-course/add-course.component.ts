import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import { CourseService } from 'src/app/providers/courseService/course.service';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  courseForm = new FormGroup({
    courseName: new FormControl(''),
    courseDesc: new FormControl(''),
    courseSkill: new FormControl(''),
    coursePrerequisites: new FormControl(''),
    courseLocation: new FormControl(''),
    creatorId: new FormControl('')
  });

  constructor(private snackBar: MatSnackBar, private router: Router, private courseService: CourseService) { }

  ngOnInit() {
    this.courseForm.patchValue({
      creatorId: sessionStorage.getItem('userId')
    });
  }

  addCourse() {
    this.courseService.addCourse(this.courseForm.value);
    this.openSnackBar('Added Successfully', 'Done!');
    // this.router.navigate([`/manage`]);
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
