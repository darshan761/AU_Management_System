/**
 * 
 */
package com.accolite.aums.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.accolite.aums.models.TrainingMaterial;

/**
 * @author darshan
 *
 */
public interface TrainingMaterialService {

	public List<TrainingMaterial> getAllTrainingMaterials();

	public List<TrainingMaterial> findTrainingMaterialById(int courseId, int instructorId);

	public void updateTrainingMaterial(TrainingMaterial trainingMaterial);

	public void deleteTrainingMaterial(int id);

	public void addTrainingMaterial(MultipartFile[] file, int courseId, int instructorId) throws SerialException, IOException, SQLException;
}
