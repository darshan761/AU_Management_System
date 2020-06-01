/**
 * 
 */
package com.accolite.aums.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.aums.models.Email;
import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.service.impl.EmailServiceImpl;
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
	
	@Autowired
	private EmailServiceImpl emailService;

	@GetMapping("/")
	public List<TrainingMaterial> getAlltrainingMaterials() {
		
		return trainingMaterialService.getAllTrainingMaterials();
	}
	
	@GetMapping("/{courseId}/{instructorId}")
	public List<TrainingMaterial> getTrainingMaterialById(@PathVariable("courseId") int courseId,@PathVariable("instructorId") int instructorId) {
		
		return trainingMaterialService.findTrainingMaterialById(courseId, instructorId);
	}
	
	@PostMapping("/add")
	public void addTraining(@RequestParam("file[]") MultipartFile[] file, @RequestParam("courseId") Integer courseId,
            @RequestParam("instructorId") int instructorId) throws SerialException, IOException, SQLException {
		trainingMaterialService.addTrainingMaterial(file, courseId, instructorId);
	}
	
	@PutMapping("/save")
	public void updateTraining(@RequestBody TrainingMaterial trainingMaterial) {
		trainingMaterialService.updateTrainingMaterial(trainingMaterial);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteTrainingMaterial(@PathVariable("id") int id) {
		trainingMaterialService.deleteTrainingMaterial(id);
	}
	
	@PostMapping("/mail")
	public void sendEmail(@RequestBody Email email) throws MailException, MessagingException {
		emailService.sendEmail(email);
	}
}
