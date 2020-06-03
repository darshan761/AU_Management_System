import { Component, OnInit, Inject, Optional } from '@angular/core';
import {FormControl} from '@angular/forms';
import { Course } from 'src/app/models/Course';
import { Observable, throwError } from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { catchError, retry } from 'rxjs/operators';
import { CourseService } from 'src/app/providers/courseService/course.service';
import { ManageCourseComponent } from '../manage-course/manage-course.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TrainingService } from 'src/app/providers/trainingService/training.service';
import { UserService } from 'src/app/providers/userService/user.service';
import { ApiResponse } from 'src/app/models/ApiResponse';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  CourseList = [];
  TrainingList = [ ];
  InstructorList = [];
  isShowDiv: boolean[]= new Array(100);
  filteredCourseList : Observable<Course[]>;
  myControl = new FormControl();

  constructor(private courseService: CourseService, private trainingService: TrainingService, private userService: UserService) { }

  ngOnInit() {
    // this.courseService.getAllCourse().subscribe((data: Course) => this.CourseList );
    this.isShowDiv.fill( true);
    this.courseService.getAllCourse().subscribe((response: ApiResponse) => {
      console.log(response);
      this.CourseList = response.data;
      this.filteredCourseList = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => typeof value === 'string' ? value : value.userEmail),
        map(name => name ? this._filter(name) : this.CourseList.slice())
      );
    });
  }

  private _filter(name: string) {
    const filterValue = name.toLowerCase();
    console.log(name);
    return this.CourseList.filter(course => course.courseName.toLowerCase().indexOf(filterValue) === 0);
  }

  showTraining(courseId, instructorId){
    // this.isShowDiv[index] = !this.isShowDiv[index];
    this.trainingService.getTrainingByInstructorID(courseId, instructorId).subscribe((response: ApiResponse)=>{
      console.log("dataaaa",response.data);
      if(response.data.length != 0) {
        this.TrainingList.push(response.data);
        console.log(this.TrainingList);
      }
    });
  }

  checkIfNoMaterialUpload(instructorId){
    let count = 0;
    
    for(let t of this.TrainingList){
      
      for(let i of t){
        console.log("hola",i.instructorId ,instructorId);
        if(i.instructorId != instructorId){
          count++;
        }
      }
    }
    console.log(count,this.TrainingList.length);
    if(count === this.TrainingList.length) return true;
    else return false;
  }

  showInstructor(courseId, index){
    this.isShowDiv[index] = !this.isShowDiv[index];
    this.userService.getInstructorByCourse(courseId).subscribe((response: ApiResponse)=>{
      this.InstructorList = response.data;
      this.TrainingList = [];
      for(let instructor of this.InstructorList){
        this.showTraining(courseId, instructor.userId);
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
