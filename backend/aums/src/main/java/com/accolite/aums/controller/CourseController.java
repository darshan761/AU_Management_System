/**
 * 
 */
package com.accolite.aums.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;
import com.accolite.aums.service.impl.CourseServiceImpl;

/**
 * @author darshan
 *
 */
@RestController
@RequestMapping("api/course")
public class CourseController {
	
	
	@Autowired
	private CourseServiceImpl courseService;

	@GetMapping("/")
	public ResponseDto getAllCourses() {
		
		return courseService.getAllCourses();
	}
	
	@GetMapping("/{id}")
	public ResponseDto getCourse(@PathVariable("id") int id) {
		
		return courseService.findCourseById(id);
	}
	
	@GetMapping("/creator/{id}")
	public ResponseDto getCourseByCreatorId(@PathVariable("id") int id) {
		
		return courseService.findCoursesByUserId(id);
	}
	
	@GetMapping("/instructor/{id}")
	public ResponseDto getCourseByInstructorId(@PathVariable("id") int id) {
		
		return courseService.findCoursesByInstructorId(id);
	}
	
	@GetMapping("/count")
	public ResponseDto getCourseCount() {
		return courseService.getCourseCount();
	}
	
	@PostMapping("/add")
	public ResponseDto addCourse(@RequestBody Course course) {
		return courseService.addCourse(course);
	}
	
	@PostMapping("/save")
	public ResponseDto updateCourse(@RequestBody Course course) {
		return courseService.updateCourse(course);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseDto deleteCourse(@PathVariable("id") int id) {
		return courseService.deleteCourse(id);
	}
	
}
