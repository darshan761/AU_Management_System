/**
 * 
 */
package com.accolite.aums.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.aums.dao.TrainingDao;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.models.Training;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.InstructorRowMapper;
import com.accolite.aums.rowmapper.TrainingRowMapper;

/**
 * @author darshan
 *
 */
@Repository
public class TrainingDaoImpl implements TrainingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseDto getAllTrainings() { 
		
		ResponseDto response = new ResponseDto();		
		try {
			response.setData(jdbcTemplate.query(Queries.GET_ALL_TRAINING, TrainingRowMapper.TrainingRowMapperLambda));
			response.setResponseType(ResponseType.SUCCESS);
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
		}
		return response;
	}

	@Override
	public ResponseDto findTrainingById(int id) {
 
		ResponseDto response = new ResponseDto();		
		try {
			response.setData(jdbcTemplate.queryForObject(Queries.GET_TRAINING_BY_ID, TrainingRowMapper.TrainingRowMapperLambda, id));
			response.setResponseType(ResponseType.SUCCESS);
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
		}
		return response;
	}

	@Override
	public ResponseDto addTraining(Training training) {
		ResponseDto response = new ResponseDto();		
		try {
			jdbcTemplate.update(Queries.CREATE_TRAINING, training.getCourseId(), training.getInstructorId(), training.getFeedback());
			response.setResponseType(ResponseType.SUCCESS);
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
		}
		return response;
	}

	@Override
	public ResponseDto deleteTraining(int id) {
		ResponseDto response = new ResponseDto();		
		try {
			jdbcTemplate.update(Queries.DELETE_TRAINING, id);
			response.setResponseType(ResponseType.SUCCESS);
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
		}
		return response;
	}


}
