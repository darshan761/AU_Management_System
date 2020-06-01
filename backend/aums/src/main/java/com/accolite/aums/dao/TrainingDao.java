/**
 * 
 */
package com.accolite.aums.dao;

import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Training;

/**
 * @author darshan
 *
 */
public interface TrainingDao {
	public ResponseDto getAllTrainings();
	 
	 public ResponseDto findTrainingById(int id);
	 
	 public ResponseDto addTraining(Training training);
	 	 
	 public ResponseDto deleteTraining(int id);
}
