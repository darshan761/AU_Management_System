import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/providers/courseService/course.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { TrainingComponent } from '../training/training.component';
import { TrainingService } from 'src/app/providers/trainingService/training.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-training-details',
  templateUrl: './training-details.component.html',
  styleUrls: ['./training-details.component.css']
})
export class TrainingDetailsComponent implements OnInit {

  CourseList = [ ];
  TrainingList = [ ];
  isShowDiv: boolean[]= new Array(100);
  fileUrl;
  constructor(private sanitizer: DomSanitizer, private trainingService: TrainingService, private courseService: CourseService, public dialog: MatDialog) { }

  ngOnInit() {
    this.isShowDiv.fill( true);
    this.courseService.getCourseByInstructorId().subscribe((data:any)=>{
      console.log(data);
      this.CourseList = data;
    });
  }

  openDialog(courseId): void {
    const dialogRef = this.dialog.open(TrainingComponent, {
      width: '600px',
      height: '350px',
      data: courseId
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  showTraining(courseId, index){
    this.isShowDiv[index] = !this.isShowDiv[index];
    this.trainingService.getTrainingByInstructor(courseId).subscribe((data:any)=>{
      this.TrainingList = data;
      console.log('frg',this.TrainingList);
    });
    console.log(this.TrainingList);
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

  deleteTraining(trainingId){
    if(confirm('You Sure want to delete the Instructor?')){
      this.trainingService.deleteTraining(trainingId);
      this.ngOnInit();
    }
  }

}
