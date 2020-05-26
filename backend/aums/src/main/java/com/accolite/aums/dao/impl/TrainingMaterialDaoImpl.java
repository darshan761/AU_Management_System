/**
 * 
 */
package com.accolite.aums.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.aums.dao.TrainingMaterialDao;
import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.TrainingMaterialRowMapper;
import com.accolite.aums.rowmapper.TrainingRowMapper;

/**
 * @author darshan
 *
 */
@Repository
public class TrainingMaterialDaoImpl implements TrainingMaterialDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<TrainingMaterial> getAllTrainingMaterials() {
		return jdbcTemplate.query(Queries.GET_ALL_TRAINING_MATERIAL,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda);
	}

	@Override
	public TrainingMaterial findTrainingMaterialById(int id) {
		return jdbcTemplate.queryForObject(Queries.GET_TRAINING_MATERIAL_BY_ID,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, id);
	}

	@Override
	public void addTrainingMaterial(TrainingMaterial trainingMaterial) {
		jdbcTemplate.update(Queries.CREATE_TRAINING_MATERIAL, trainingMaterial.getTrainingId(),
				trainingMaterial.getFileName(), trainingMaterial.getFileType(), trainingMaterial.getFile());

	}

	@Override
	public void updateTrainingMaterial(TrainingMaterial trainingMaterial) {
		jdbcTemplate.update(Queries.UPDATE_TRAINING_MATERIAL, trainingMaterial.getFileName(),
				trainingMaterial.getFileType(), trainingMaterial.getFile(), trainingMaterial.getTrainingId());
	}

	@Override
	public void deleteTrainingMaterial(int id) {
		jdbcTemplate.update(Queries.DELETE_TRAINING_MATERIAL, id);

	}

}
