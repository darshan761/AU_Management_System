import { Component, Inject, Optional, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ManageCourseComponent } from '../manage-course/manage-course.component';
import {FormControl, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { Users } from 'src/app/models/Users';
import { UserService } from 'src/app/providers/userService/user.service';
import { Course } from 'src/app/models/Course';
import { Training } from 'src/app/models/Training';
import { ApiResponse } from 'src/app/models/ApiResponse';
import { MatSnackBar } from '@angular/material';

export interface User {
  userName: string;
  userEmail: string;
  userId: number;
}


@Component({
  selector: 'app-instructor',
  templateUrl: './instructor.component.html',
  styleUrls: ['./instructor.component.css']
})
export class InstructorComponent  implements OnInit{

  courseId;
  action;
  training = new Training();
  myControl = new FormControl('', Validators.required);
  userList: User[ ];
  filteredOptions: Observable<User[]>;
  InstructorList = [ ];
  received = false;

  constructor(private snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<ManageCourseComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService) {
      this.userService.getAllUsers().subscribe((response:any) =>{
        this.userList = response.data;

        this.filteredOptions = this.myControl.valueChanges
        .pipe(
          startWith(''),
          map(value => typeof value === 'string' ? value : value.userEmail),
          map(name => name ? this._filter(name) : this.userList.slice())
        );
        // console.log(this.userList);
      });
    }

  onNoClick(): void {
    console.log(this.data);
    this.dialogRef.close();
  }

  ngOnInit() {
    this.courseId = this.data.courseId;
    this.action = this.data.action;
    this.userService.getInstructorByCourse(this.data.courseId).subscribe((response: ApiResponse)=>{
      this.InstructorList = response.data;
    });

  }

  displayFn(user: User): string {
    return user && user.userEmail? user.userEmail : '';
  }

  
  private _filter(name: string): User[] {
    const filterValue = name.toLowerCase();
    console.log('name',name);
    console.log('Print ho jaaa', this.userList);
    return this.userList.filter(option => option.userEmail.toLowerCase().indexOf(filterValue) === 0);
  }

  assignInstructor() {
    this.training.instructorId = this.myControl.value.userId;
    this.training.courseId = this.data.courseId;
    this.userService.addInstructor(this.training);
    this.dialogRef.close();
  }

  deleteInstructor(trainingId){
    if(confirm('You Sure want to delete the Instructor?')){
      this.userService.deleteInstructor(trainingId);
      this.openSnackBar('deleted Successfully', 'Done!');
      this.ngOnInit();
    }
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

  checkAssign(): boolean {
    if(this.data.action === 'assign') return true;
    else return false;
  }

  checkView(): boolean {
    if(this.data.action === 'view') return true;
    else return false;
  }

}
