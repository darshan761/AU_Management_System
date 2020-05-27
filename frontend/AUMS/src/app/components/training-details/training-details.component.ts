import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/providers/courseService/course.service';

@Component({
  selector: 'app-training-details',
  templateUrl: './training-details.component.html',
  styleUrls: ['./training-details.component.css']
})
export class TrainingDetailsComponent implements OnInit {

  CourseList = [ ];
  constructor(private courseService: CourseService) { }

  ngOnInit() {
    this.courseService.getCourseByInstructorId().subscribe((data:any)=>{
      console.log(data);
      this.CourseList = data;
    });
  }

}
