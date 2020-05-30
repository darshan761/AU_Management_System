/**
 * 
 */
package com.accolite.aums.dao.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.aums.dao.TrainingMaterialDao;
import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.TrainingMaterialRowMapper;

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
	public List<TrainingMaterial> findTrainingMaterialById(int courseId, int instructorId) {
		return jdbcTemplate.query(Queries.GET_TRAINING_MATERIAL_BY_IDS,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, courseId, instructorId);
	}

	@Override
	public void addTrainingMaterial(MultipartFile[] files, int courseId,int  instructorId) throws IOException, SerialException, SQLException {

		int trainingId = jdbcTemplate.queryForObject(Queries.GET_TRAINING_ID_FROM_MATERIAL, Integer.class, courseId, instructorId);
		for(MultipartFile file : files) {
			byte[] bytes = file.getBytes();
		    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			jdbcTemplate.update(Queries.CREATE_TRAINING_MATERIAL, trainingId, blob, file.getOriginalFilename(), file.getContentType(), file.getSize());
		}

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
