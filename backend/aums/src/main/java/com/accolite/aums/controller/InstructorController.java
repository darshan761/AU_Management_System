/**
 * 
 */
package com.accolite.aums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.aums.dto.ResponseDto;
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
	public ResponseDto getAlInstructors() {
		
		return instructorService.getAllInstructors();
	}
	
	@GetMapping("/{id}")
	public ResponseDto getInstructor(@PathVariable("id") int id) {
		
		return instructorService.findInstructorById(id);
	}
	
	@GetMapping("/course/{id}")
	public ResponseDto getInstructorByCourseId(@PathVariable("id") int id) {
		
		return instructorService.findInstructorByCourseId(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseDto deleteInstructor(@PathVariable("id") int id) {
		return instructorService.deleteInstructor(id);
	}

}
