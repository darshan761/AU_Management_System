/**
 * 
 */
package com.accolite.aums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.aums.dao.impl.TrainingDaoImpl;
import com.accolite.aums.dto.ResponseDto;
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
	public ResponseDto getAllTrainings() {
		return trainingDao.getAllTrainings();
	}

	@Override
	public ResponseDto findTrainingById(int id) {
		return trainingDao.findTrainingById(id);
	}

	@Override
	public ResponseDto addTraining(Training training) {
		return trainingDao.addTraining(training);
		
	}

	@Override
	public ResponseDto deleteTraining(int id) {
		return trainingDao.deleteTraining(id);
		
	}

}
 