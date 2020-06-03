import { Component, OnInit, ViewChild } from '@angular/core';
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
import { ApiResponse } from 'src/app/models/ApiResponse';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';

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

  displayedColumns: string[] = ['courseId', 'courseName', 'courseDesc', 'courseLocation', 'coursePrerequisites', 'courseSkill', 'edit', 'delete', 'add' ,'view'];
  CourseList = [ ];
  InstructorList = [ ];
  myControl = new FormControl();
  dataSource: MatTableDataSource<Course>;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private snackBar: MatSnackBar, private courseService: CourseService, private userService: UserService, public dialog: MatDialog) { 
  }

  ngOnInit() {
    this.courseService.getCourseByCreatorId().subscribe((response: ApiResponse) => {
      console.log(response);
      this.CourseList = response.data;
      this.dataSource = new MatTableDataSource(this.CourseList);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  openDialog(courseId): void {
    const dialogRef = this.dialog.open(InstructorComponent, {
      width: '60%',
      height: '40%',
      data: {
        courseId : courseId,
        action : 'assign'
      }

    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openInstructorDialog(courseId): void {
    const dialogRef = this.dialog.open(InstructorComponent, {
      width: '60%',
      height: '70%',
      data: {
        courseId : courseId,
        action : 'view'
      }

    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }


  openCreateDialog() {
    const dialogRef = this.dialog.open(AddCourseComponent, {
      width: '50%',
      height: '90%'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openViewCourseDialog( courseId) {
    const dialogRef = this.dialog.open(CourseDetailsComponent, {
      width: '50%',
      height: '90%',
      data: courseId
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
