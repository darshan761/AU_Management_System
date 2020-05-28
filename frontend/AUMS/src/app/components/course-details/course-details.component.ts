import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from 'src/app/providers/courseService/course.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css']
})
export class CourseDetailsComponent implements OnInit {

  isDisabled = true;
  courseId: any;
  course: any;
  skills = [];
  prerequisites = [];

  courseForm = new FormGroup({
    courseName: new FormControl(''),
    courseDesc: new FormControl(''),
    courseSkill: new FormControl(''),
    coursePrerequisites: new FormControl(''),
    courseLocation: new FormControl(''),
    courseId: new FormControl('')
  });

  constructor(private snackBar: MatSnackBar, private route: Router, private router: ActivatedRoute, private courseService: CourseService) { }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.courseId = +params['id'];
      this.courseService.getCourseById(this.courseId).subscribe((data:any)=>{
      console.log(data);
      this.course = data;
      this.courseForm.setValue({
        courseName: data.courseName,
        courseDesc: data.courseDesc,
        courseSkill: data.courseSkill,
        coursePrerequisites: data.coursePrerequisites,
        courseLocation: data.courseLocation,
        courseId: this.courseId
      });
    });
   });
  }

  saveCourse(){
    this.courseService.updateCourse(this.courseForm.value);
    console.log(this.courseForm);

    this.openSnackBar('edited Successfully', 'Done!');
    // alert('Edited Successfully');
    this.route.navigate([`/manage`]);
    this.disable();
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

  enable() {
    this.isDisabled = false;
 }

 disable(){
    this.isDisabled = true;
 }


}
