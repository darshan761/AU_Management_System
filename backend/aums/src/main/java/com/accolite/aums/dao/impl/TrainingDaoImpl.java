/**
 * 
 */
package com.accolite.aums.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.aums.dao.TrainingDao;
import com.accolite.aums.models.Training;
import com.accolite.aums.queries.Queries;
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
	public List<Training> getAllTrainings() {
		return jdbcTemplate.query(Queries.GET_ALL_TRAINING, TrainingRowMapper.TrainingRowMapperLambda);
	}

	@Override
	public Training findTrainingById(int id) {
		return jdbcTemplate.queryForObject(Queries.GET_TRAINING_BY_ID, TrainingRowMapper.TrainingRowMapperLambda, id);
	}

	@Override
	public void addTraining(Training training) {
		jdbcTemplate.update(Queries.CREATE_TRAINING, training.getCourseId(), training.getInstructorId(), training.getFeedback());
		
	}

	@Override
	public void updateTraining(Training training) {
		jdbcTemplate.update(Queries.UPDATE_TRAINING,  training.getCourseId(), training.getInstructorId(), training.getFeedback(),training.getTrainingId());

	}

	@Override
	public void deleteTraining(int id) {
		jdbcTemplate.update(Queries.DELETE_TRAINING, id);
	}


}
