/**
 * 
 */
package com.accolite.aums.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public TrainingMaterial findTrainingMaterialById(int id) {
		return trainingMaterialDao.findTrainingMaterialById(id);
	}

	@Override
	public void addTrainingMaterial(TrainingMaterial trainingMaterial) {
		trainingMaterialDao.addTrainingMaterial(trainingMaterial);

	}

	@Override
	public void updateTrainingMaterial(TrainingMaterial trainingMaterial) {
		trainingMaterialDao.updateTrainingMaterial(trainingMaterial);

	}

	@Override
	public void deleteTrainingMaterial(int id) {
		trainingMaterialDao.deleteTrainingMaterial(id);

	}

}
