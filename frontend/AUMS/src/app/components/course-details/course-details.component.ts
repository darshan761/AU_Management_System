import { Component, OnInit, Inject, Optional } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from 'src/app/providers/courseService/course.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ManageCourseComponent } from '../manage-course/manage-course.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css']
})
export class CourseDetailsComponent implements OnInit {

  isDisabled = true;
  courseId: any;
  course: any;
  inputColor = 'grey';
  skills = [];
  prerequisites = [];

  courseForm = new FormGroup({
    courseName: new FormControl('', Validators.required),
    courseDesc: new FormControl('', Validators.required),
    courseSkill: new FormControl('', Validators.required),
    coursePrerequisites: new FormControl('', Validators.required),
    courseLocation: new FormControl('', Validators.required),
    courseId: new FormControl('', Validators.required)
  });

  constructor(public dialogRef: MatDialogRef<ManageCourseComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: number, private snackBar: MatSnackBar, private route: Router, private router: ActivatedRoute, private courseService: CourseService) { }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.courseId = this.data;
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
    this.inputColor = 'black';
    this.isDisabled = false;
 }

 disable(){
  this.inputColor = 'grey';
  this.isDisabled = true;
 }

 getColor(){
   return this.inputColor;
 }

}
