import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/providers/courseService/course.service';
import { UserService } from 'src/app/providers/userService/user.service';

import { Router } from '@angular/router';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { InstructorComponent } from '../instructor/instructor.component';
import { AddCourseComponent } from '../add-course/add-course.component';
import { CoursesComponent } from '../courses/courses.component';
import { CourseDetailsComponent } from '../course-details/course-details.component';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Course } from 'src/app/models/Course';
import { FormControl } from '@angular/forms';
import { startWith, map } from 'rxjs/operators';

// export interface DialogData {
//   animal: string;
//   name: string;
// }

@Component({
  selector: 'app-manage-course',
  templateUrl: './manage-course.component.html',
  styleUrls: ['./manage-course.component.css']
})
export class ManageCourseComponent implements OnInit {
  CourseList = [ ];
  InstructorList = [ ];
  filteredCourseList : Observable<Course[]>;
  isShowDiv: boolean[]= new Array(100);
  myControl = new FormControl();

  constructor(private snackBar: MatSnackBar, private courseService: CourseService, private userService: UserService, public dialog: MatDialog) { 
  }

  openDialog(courseId): void {
    const dialogRef = this.dialog.open(InstructorComponent, {
      width: '60%',
      height: '40%',
      data: courseId
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openCreateDialog() {
    const dialogRef = this.dialog.open(AddCourseComponent, {
      width: '60%',
      height: '90%'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openViewCourseDialog( courseId) {
    const dialogRef = this.dialog.open(CourseDetailsComponent, {
      width: '60%',
      height: '90%',
      data: courseId
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  ngOnInit() {
    this.isShowDiv.fill( true);
    this.courseService.getCourseByCreatorId().subscribe((data:any)=>{
      console.log(data);
      this.CourseList = data;
      this.filteredCourseList = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => typeof value === 'string' ? value : value.userEmail),
        map(name => name ? this._filter(name) : this.CourseList.slice())
      );
    });
  }

  private _filter(name: string) {
    const filterValue = name.toLowerCase();
    console.log(name);
    return this.CourseList.filter(course => course.courseName.toLowerCase().indexOf(filterValue) === 0);
  }

  showInstructor(courseId, index){
    this.isShowDiv[index] = !this.isShowDiv[index];
    this.userService.getInstructorByCourse(courseId).subscribe((data:any)=>{
      this.InstructorList = data;
    });
  }

  deleteInstructor(trainingId){
    if(confirm('You Sure want to delete the Instructor?')){
      this.userService.deleteInstructor(trainingId);
      this.openSnackBar('deleted Successfully', 'Done!');
      this.ngOnInit();
    }
  }

  deleteCourse(courseId) {
    console.log(courseId);
    if(confirm('You Sure want to delete the Course?')){
      this.courseService.deleteCourse(courseId);
      this.openSnackBar('deleted Successfully', 'Done!');
      this.ngOnInit();
    }
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
