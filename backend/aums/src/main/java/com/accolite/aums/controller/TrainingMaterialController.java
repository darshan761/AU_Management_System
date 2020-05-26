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

import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.service.impl.TrainingMaterialServiceImpl;

/**
 * @author darshan
 *
 */
@RestController
@RequestMapping("api/training/material")
public class TrainingMaterialController {

	@Autowired
	private TrainingMaterialServiceImpl trainingMaterialService;

	@GetMapping("/")
	public List<TrainingMaterial> getAlltrainingMaterials() {
		
		return trainingMaterialService.getAllTrainingMaterials();
	}
	
	@GetMapping("/{id}")
	public TrainingMaterial gettrainingMaterialService(@PathVariable("id") int id) {
		
		return trainingMaterialService.findTrainingMaterialById(id);
	}
	
	@PostMapping("/add")
	public void addTraining(@RequestBody TrainingMaterial trainingMaterial) {
		trainingMaterialService.addTrainingMaterial(trainingMaterial);
	}
	
	@PutMapping("/save")
	public void updateTraining(@RequestBody TrainingMaterial trainingMaterial) {
		trainingMaterialService.updateTrainingMaterial(trainingMaterial);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteTrainingMaterial(@PathVariable("id") int id) {
		trainingMaterialService.deleteTrainingMaterial(id);
	}
}
