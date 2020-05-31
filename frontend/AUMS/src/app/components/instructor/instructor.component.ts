import { Component, Inject, Optional, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ManageCourseComponent } from '../manage-course/manage-course.component';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { Users } from 'src/app/models/Users';
import { UserService } from 'src/app/providers/userService/user.service';
import { Course } from 'src/app/models/Course';
import { Training } from 'src/app/models/Training';

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
  training = new Training();
  myControl = new FormControl();
  userList: User[ ];
  filteredOptions: Observable<User[]>;

  received = false;

  constructor(
    public dialogRef: MatDialogRef<ManageCourseComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: number,
    private userService: UserService) {
      this.userService.getAllUsers().subscribe((response:any) =>{
        this.userList = response;
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

    console.log(this.userList);
    if (this.received) {
      // this.filteredOptions = this.myControl.valueChanges
      // .pipe(
      //   startWith(''),
      //   map(value => typeof value === 'string' ? value : value.userEmail),
      //   map(name => name ? this._filter(name) : this.userList.slice())
      // );
    }
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
    this.training.courseId = this.data;
    this.userService.addInstructor(this.training);
    this.dialogRef.close();
  }
}
