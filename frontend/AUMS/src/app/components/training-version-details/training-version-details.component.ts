import { Component, OnInit, Optional, Inject } from '@angular/core';
import { CoursesComponent } from '../courses/courses.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CourseService } from 'src/app/providers/courseService/course.service';
import { Course } from 'src/app/models/Course';
import { ApiResponse } from 'src/app/models/ApiResponse';
import { TrainingService } from 'src/app/providers/trainingService/training.service';

@Component({
  selector: 'app-training-version-details',
  templateUrl: './training-version-details.component.html',
  styleUrls: ['./training-version-details.component.css']
})
export class TrainingVersionDetailsComponent implements OnInit {

  fileDetails = [];

  constructor( public dialogRef: MatDialogRef<CoursesComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: number, private trainingService: TrainingService) {

     }

  ngOnInit() {
    this.trainingService.getVersionData(this.data).subscribe((response: ApiResponse)=>{
      this.fileDetails = response.data;
      console.log(response.data);
    });
  }

  base64ToArrayBuffer(base64) {
    let binary_string = window.atob(base64);
    let len = binary_string.length;
    let bytes = new Uint8Array(len);
    for (let i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
    }
    return bytes.buffer;
  }


  downloadFile(data, type){
    console.log(typeof data);
    let byteArray = this.base64ToArrayBuffer(data);
    const blob = new Blob([byteArray], { type: type });
    const url = window.URL.createObjectURL(blob);
    window.open(url);
  }

}
