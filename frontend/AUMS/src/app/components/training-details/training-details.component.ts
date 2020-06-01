import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/providers/courseService/course.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { TrainingComponent } from '../training/training.component';
import { TrainingService } from 'src/app/providers/trainingService/training.service';
import { DomSanitizer } from '@angular/platform-browser';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormControl, Validators } from '@angular/forms';

 class Email {
	subject: string;
  message; string;
  recepient: string;
  courseId: number;
  instructor: string;
}

@Component({
  selector: 'app-training-details',
  templateUrl: './training-details.component.html',
  styleUrls: ['./training-details.component.css']
})
export class TrainingDetailsComponent implements OnInit {

  CourseList = [ ];
  TrainingList = [ ];
  isShowDiv: boolean[]= new Array(100);
  email = new Email();
  myControl = new FormControl('', [Validators.email, Validators.required]);
  fileUrl;
  constructor(private snackBar: MatSnackBar, private sanitizer: DomSanitizer, private trainingService: TrainingService, private courseService: CourseService, public dialog: MatDialog) { }

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

  deleteTraining(fileId){
    console.log(fileId);
    if(confirm('You Sure want to delete the Training Material?')){
      this.trainingService.deleteTraining(fileId);
      this.ngOnInit();
    }
  }

  sendEmail(courseId) {
    this.email.subject = 'AUMS: Training Material Uploaded!';
    this.email.message = 'Training Material is Uploaded!';
    this.email.recepient = this.myControl.value;
    this.email.courseId = courseId;
    this.email.instructor = sessionStorage.getItem('userName');
    this.trainingService.sendMail(this.email).subscribe((data)=>{
    this.openSnackBar('Email Sent Successfully', 'Done!');
    }
    );
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
