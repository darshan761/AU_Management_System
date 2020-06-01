/**
 * 
 */
package com.accolite.aums.service;

import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Training;

/**
 * @author darshan
 *
 */
public interface TrainingService {
	public ResponseDto getAllTrainings();

	public ResponseDto findTrainingById(int id);

	public ResponseDto addTraining(Training training);

	public ResponseDto deleteTraining(int id);
}
