/**
 * 
 */
package com.accolite.aums.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.Multipart;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.aums.dao.impl.TrainingMaterialDaoImpl;
import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.service.TrainingMaterialService;

/**
 * @author darshan
 *
 */
@Service
public class TrainingMaterialServiceImpl implements TrainingMaterialService {

	@Autowired
	private TrainingMaterialDaoImpl trainingMaterialDao;

	@Override
	public List<TrainingMaterial> getAllTrainingMaterials() {

		return trainingMaterialDao.getAllTrainingMaterials();
	}

	@Override
	public List<TrainingMaterial> findTrainingMaterialById(int courseId, int instructorId) {
		return trainingMaterialDao.findTrainingMaterialById(courseId, instructorId);
	}

	@Override
	public void updateTrainingMaterial(TrainingMaterial trainingMaterial) {
		trainingMaterialDao.updateTrainingMaterial(trainingMaterial);

	}

	@Override
	public void deleteTrainingMaterial(int id) {
		trainingMaterialDao.deleteTrainingMaterial(id);

	}

	@Override
	public void addTrainingMaterial(MultipartFile[] file, int courseId, int instructorId) throws SerialException, IOException, SQLException {
		trainingMaterialDao.addTrainingMaterial(file, courseId, instructorId);
	}

}
