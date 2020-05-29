import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/providers/courseService/course.service';
import { UserService } from 'src/app/providers/userService/user.service';

import { Router } from '@angular/router';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { InstructorComponent } from '../instructor/instructor.component';

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
  isShowDiv: boolean[]= new Array(100);

  constructor(private courseService: CourseService, private userService: UserService, public dialog: MatDialog) { 
  }

  openDialog(courseId): void {
    const dialogRef = this.dialog.open(InstructorComponent, {
      width: '600px',
      height: '350px',
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
    });
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
      this.ngOnInit();
    }
  }

  deleteCourse(courseId) {
    console.log(courseId);
    if(confirm('You Sure want to delete the Course?')){
      this.courseService.deleteCourse(courseId);
      this.ngOnInit();
    }
  }

}
