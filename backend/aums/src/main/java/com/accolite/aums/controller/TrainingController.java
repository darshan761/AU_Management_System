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
import com.accolite.aums.models.Training;
import com.accolite.aums.service.impl.TrainingServiceImpl;

/**
 * @author darshan
 *
 */
@RestController
@RequestMapping("api/training")
public class TrainingController {

	@Autowired
	private TrainingServiceImpl trainingService;

	@GetMapping("/")
	public List<Training> getAlltrainings() {
		
		return trainingService.getAllTrainings();
	}
	
	@GetMapping("/{id}")
	public Training getTraining(@PathVariable("id") int id) {
		
		return trainingService.findTrainingById(id);
	}
	
	@PostMapping("/add")
	public void addTraining(@RequestBody Training training) {
		trainingService.addTraining(training);
	}
	
	@PutMapping("/save")
	public void updateTraining(@RequestBody Training training) {
		trainingService.updateTraining(training);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteTraining(@PathVariable("id") int id) {
		trainingService.deleteTraining(id);
	}
}
