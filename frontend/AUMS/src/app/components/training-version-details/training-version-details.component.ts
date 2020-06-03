import { Component, OnInit, Optional, Inject } from '@angular/core';
import { CoursesComponent } from '../courses/courses.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CourseService } from 'src/app/providers/courseService/course.service';
import { Course } from 'src/app/models/Course';
import { ApiResponse } from 'src/app/models/ApiResponse';

@Component({
  selector: 'app-training-version-details',
  templateUrl: './training-version-details.component.html',
  styleUrls: ['./training-version-details.component.css']
})
export class TrainingVersionDetailsComponent implements OnInit {


  course: Course[];

  constructor( public dialogRef: MatDialogRef<CoursesComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: number, private courseService: CourseService) {

     }

  ngOnInit() {
    console.log(this.data);
    this.courseService.getVersionData(this.data).subscribe((response: ApiResponse)=>{
      this.course = response.data;
      console.log(this.course);
    });
  }
}
