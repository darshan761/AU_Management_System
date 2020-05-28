import { Component, Inject, Optional } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ManageCourseComponent, DialogData } from '../manage-course/manage-course.component';

@Component({
  selector: 'app-instructor',
  templateUrl: './instructor.component.html',
  styleUrls: ['./instructor.component.css']
})
export class InstructorComponent  {

  constructor(
    public dialogRef: MatDialogRef<ManageCourseComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
