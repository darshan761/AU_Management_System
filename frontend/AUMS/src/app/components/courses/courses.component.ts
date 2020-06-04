import { Component, OnInit, Inject, Optional, ViewChild } from '@angular/core';
import {FormControl} from '@angular/forms';
import { Course } from 'src/app/models/Course';
import { Observable, throwError } from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { catchError, retry } from 'rxjs/operators';
import { CourseService } from 'src/app/providers/courseService/course.service';
import { ManageCourseComponent } from '../manage-course/manage-course.component';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog, MatPaginator, MatTableDataSource } from '@angular/material';
import {MatSort} from '@angular/material/sort';

import { TrainingService } from 'src/app/providers/trainingService/training.service';
import { UserService } from 'src/app/providers/userService/user.service';
import { ApiResponse } from 'src/app/models/ApiResponse';
import { TrainingVersionDetailsComponent } from '../training-version-details/training-version-details.component';
import { TrainingMaterialComponent } from '../training-material/training-material.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  displayedColumns: string[] = ['courseId', 'courseName', 'courseDesc', 'courseMonth', 'courseLocation', 'coursePrerequisites', 'courseSkill', 'training', 'version'];
  CourseList = [];
  TrainingList = [ ];
  InstructorList = [];
  dataSource: MatTableDataSource<Course>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  myControl = new FormControl();

  constructor(private courseService: CourseService, private trainingService: TrainingService, private userService: UserService, 
    public dialog: MatDialog) { }

  ngOnInit() {
    // this.courseService.getAllCourse().subscribe((data: Course) => this.CourseList );

    this.courseService.getAllCourse().subscribe((response: ApiResponse) => {
      console.log(response);
      this.CourseList = response.data;
      this.dataSource = new MatTableDataSource(this.CourseList);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      console.log(this.dataSource);
    });
  }

  openVersionDialog(courseId): void {
    const dialogRef = this.dialog.open(TrainingVersionDetailsComponent, {
      width: '80%',
      height: '80%',
      data: courseId
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openTrainingDialog(courseId): void {
    const dialogRef = this.dialog.open(TrainingMaterialComponent, {
      width: '50%',
      height: '70%',
      data: courseId
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
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


