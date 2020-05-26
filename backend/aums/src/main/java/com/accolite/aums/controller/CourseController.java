/**
 * 
 */
package com.accolite.aums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public List<Course> getAllCourses() {
		
		return courseService.getAllCourses();
	}
	
	@GetMapping("/{id}")
	public Course getCourse(@PathVariable("id") int id) {
		
		return courseService.findCourseById(id);
	}
	
	@PostMapping("/add")
	public void addCourse(@RequestBody Course course) {
		courseService.addCourse(course);
	}
	
	@PutMapping("/save")
	public void updateCourse(@RequestBody Course course) {
		courseService.updateCourse(course);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCourse(@PathVariable("id") int id) {
		courseService.deleteCourse(id);
	}
	
}
