/**
 * 
 */
package com.accolite.aums.dao;

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
public interface TrainingMaterialDao {
	public List<TrainingMaterial> getAllTrainingMaterials();

	public List<TrainingMaterial> findTrainingMaterialById(int courseId, int instructorId);

	public void addTrainingMaterial(MultipartFile[] files, int courseId,int  instructorId) throws IOException, SerialException, SQLException;

	public void updateTrainingMaterial(TrainingMaterial trainingMaterial);

	public void deleteTrainingMaterial(int id);
}
