import { Component, OnInit, Inject, Optional } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { UserService } from 'src/app/providers/userService/user.service';
import {MatSnackBar} from '@angular/material/snack-bar';


export interface TrainingMaterial {
  instructorId: number;
  courseId: number;
  file: string;
}

@Component({
  selector: 'app-training',
  templateUrl: './training.component.html',
  styleUrls: ['./training.component.css']
})
export class TrainingComponent implements OnInit {

  instructorId = sessionStorage.getItem('userId');
  files: any = [];
  constructor(private snackBar: MatSnackBar, private userService: UserService, public dialogRef: MatDialogRef<TrainingComponent>, 
  @Optional() @Inject(MAT_DIALOG_DATA) public data: number) { }

  ngOnInit() {
  }

  onNoClick(): void {
    console.log(this.data);
    this.dialogRef.close();
  }

  uploadFile(event) {
    console.log(event);
    for (let index of event ) {
      const element = event[index];
      this.files.push(index);
    }
  }
  deleteAttachment(index) {
    this.files.splice(index, 1);
  }

  addFiles() {
    this.userService.addTrainingMaterial(this.files, this.data, this.instructorId);
    this.openSnackBar('File Added Successfully', 'Done!');
    this.dialogRef.close();
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
