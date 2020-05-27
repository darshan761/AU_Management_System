import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/providers/courseService/course.service';


@Component({
  selector: 'app-manage-course',
  templateUrl: './manage-course.component.html',
  styleUrls: ['./manage-course.component.css']
})
export class ManageCourseComponent implements OnInit {
  CourseList = [ ];

  constructor(private courseService: CourseService) { }

  ngOnInit() {
    this.courseService.getCourseByCreatorId().subscribe((data:any)=>{
      console.log(data);
      this.CourseList = data;
    });
  }

}
