import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/providers/courseService/course.service';
import { Router } from '@angular/router';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { InstructorComponent } from '../instructor/instructor.component';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-manage-course',
  templateUrl: './manage-course.component.html',
  styleUrls: ['./manage-course.component.css']
})
export class ManageCourseComponent implements OnInit {
  CourseList = [ ];
  animal: string;
  name: string;

  constructor(private courseService: CourseService, public dialog: MatDialog) { }

  openDialog(): void {
    const dialogRef = this.dialog.open(InstructorComponent, {
      width: '600px',
      height: '350px',
      data: {name: this.name, animal: this.animal}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  ngOnInit() {
    this.courseService.getCourseByCreatorId().subscribe((data:any)=>{
      console.log(data);
      this.CourseList = data;
    });
  }

}
