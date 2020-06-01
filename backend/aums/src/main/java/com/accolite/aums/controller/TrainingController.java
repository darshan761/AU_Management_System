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
	public ResponseDto getAlltrainings() {
		
		return trainingService.getAllTrainings();
	}
	
	@GetMapping("/{id}")
	public ResponseDto getTraining(@PathVariable("id") int id) {
		
		return trainingService.findTrainingById(id);
	}
	
	@PostMapping("/add")
	public ResponseDto addTraining(@RequestBody Training training) {
		return trainingService.addTraining(training);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseDto deleteTraining(@PathVariable("id") int id) {
		return trainingService.deleteTraining(id);
	}
}
