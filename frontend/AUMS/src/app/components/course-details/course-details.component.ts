import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from 'src/app/providers/courseService/course.service';
import { Course } from 'src/app/models/Course';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css']
})
export class CourseDetailsComponent implements OnInit {

  courseId: any;
  course: any;
  skills = [];
  prerequisites = [];

  constructor(private router: ActivatedRoute, private courseService: CourseService) { }

  ngOnInit() {
    this.router.params.subscribe(params => {
      console.log(params);
      console.log(+params['id']);
      this.courseId = +params['id'];
      this.courseService.getCourseById(this.courseId).subscribe((data:any)=>{
      console.log(data);
      this.course = data;

      this.skills = this.course.courseSkill.split(',');
      this.prerequisites = this.course.coursePrerequisites.split(',');
    });
   });
  }

}
