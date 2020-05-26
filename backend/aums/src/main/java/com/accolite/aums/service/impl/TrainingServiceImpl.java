/**
 * 
 */
package com.accolite.aums.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.aums.dao.impl.TrainingDaoImpl;
import com.accolite.aums.models.Training;
import com.accolite.aums.service.TrainingService;

/**
 * @author darshan
 *
 */
@Service
public class TrainingServiceImpl implements TrainingService{

	@Autowired
	private TrainingDaoImpl trainingDao;
	
	@Override
	public List<Training> getAllTrainings() {
		return trainingDao.getAllTrainings();
	}

	@Override
	public Training findTrainingById(int id) {
		return trainingDao.findTrainingById(id);
	}

	@Override
	public void addTraining(Training training) {
		trainingDao.addTraining(training);
		
	}

	@Override
	public void updateTraining(Training training) {
		trainingDao.updateTraining(training);
		
	}

	@Override
	public void deleteTraining(int id) {
		trainingDao.deleteTraining(id);
		
	}

}
 