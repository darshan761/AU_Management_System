import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/Course';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { CourseService } from 'src/app/providers/courseService/course.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  CourseList = [];

  constructor(private courseService: CourseService) { }

  ngOnInit() {
    // this.courseService.getAllCourse().subscribe((data: Course) => this.CourseList );
    this.courseService.getAllCourse().subscribe((data: any[])=>{
      console.log(data);
      this.CourseList = data;
    });
  }


}
