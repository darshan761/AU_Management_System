import { Component, OnInit, Inject, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { CoursesComponent } from '../courses/courses.component';
import { ApiResponse } from 'src/app/models/ApiResponse';
import { TrainingService } from 'src/app/providers/trainingService/training.service';
import { UserService } from 'src/app/providers/userService/user.service';

@Component({
  selector: 'app-training-material',
  templateUrl: './training-material.component.html',
  styleUrls: ['./training-material.component.css']
})
export class TrainingMaterialComponent implements OnInit {

  displayedColumns: string[] = ['instructor', 'trainingMaterial'];
  InstructorList=[] ;
  TrainingList= [];
  constructor(public dialogRef: MatDialogRef<CoursesComponent>, 
    @Optional() @Inject(MAT_DIALOG_DATA) public data: number, private trainingService: TrainingService, private userService: UserService) { }

  ngOnInit() {
    this.userService.getInstructorByCourse(this.data).subscribe((response: ApiResponse)=>{
      this.InstructorList = response.data;
      this.TrainingList = [];
      for(let instructor of this.InstructorList){
        this.showTraining( instructor.userId);
      }
    });
  }

  showTraining(instructorId){
    // this.isShowDiv[index] = !this.isShowDiv[index];
    this.trainingService.getTrainingByInstructorID(this.data, instructorId).subscribe((response: ApiResponse)=>{
      console.log("dataaaa",response.data);
      if(response.data.length != 0) {
        this.TrainingList.push(response.data);
        console.log(this.TrainingList);
      }
    });
  }

  showInstructor(){
    this.userService.getInstructorByCourse(this.data).subscribe((response: ApiResponse)=>{
      this.InstructorList = response.data;
      this.TrainingList = [];
      for(let instructor of this.InstructorList){
        this.showTraining( instructor.userId);
      }
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
