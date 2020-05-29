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
import com.accolite.aums.models.Instructor;
import com.accolite.aums.service.impl.InstructorServiceImpl;

/**
 * @author darshan
 *
 */
@RestController
@RequestMapping("api/instructor")
public class InstructorController {
	
	@Autowired
	private InstructorServiceImpl instructorService;

	@GetMapping("/")
	public List<Instructor> getAlInstructors() {
		
		return instructorService.getAllInstructors();
	}
	
	@GetMapping("/{id}")
	public Instructor getInstructor(@PathVariable("id") int id) {
		
		return instructorService.findInstructorById(id);
	}
	
	@GetMapping("/course/{id}")
	public List<Instructor> getInstructorByCourseId(@PathVariable("id") int id) {
		
		return instructorService.findInstructorByCourseId(id);
	}
	
//	@PostMapping("/add")
//	public void addInstructor(@RequestBody Instructor instructor) {
//		instructorService.addInstructor(instructor);
//	}
//	
//	@PutMapping("/save")
//	public void updateInstructor(@RequestBody Instructor instructor) {
//		instructorService.updateInstructor(instructor);
//	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteInstructor(@PathVariable("id") int id) {
		instructorService.deleteInstructor(id);
	}

}
