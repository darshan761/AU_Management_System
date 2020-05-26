/**
 * 
 */
package com.accolite.aums.service;

import java.util.List;

import com.accolite.aums.models.Training;

/**
 * @author darshan
 *
 */
public interface TrainingService {
	public List<Training> getAllTrainings();
	 
	 public Training findTrainingById(int id);
	 
	 public void addTraining(Training training);
	 
	 public void updateTraining(Training training);
	 
	 public void deleteTraining(int id);
}
