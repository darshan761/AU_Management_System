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

   course1: Course = {
    id: 1,
    name: 'React',
    description: 'My Description',
    skills : 'ReactJS, React-native',
    prerequisites: 'HTML,JSX'
  };

  course2: Course = {
    id: 2,
    name: 'Angular',
    description: 'Your Description',
    skills : 'ReactJS, React-native',
    prerequisites: 'HTML,JSX'
  };

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
