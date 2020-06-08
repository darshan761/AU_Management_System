/**
 * 
 */
package com.accolite.aums.dao.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.aums.dao.TrainingMaterialDao;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.TrainingMaterialRowMapper;

/**
 * @author darshan
 *
 */
@Repository
public class TrainingMaterialDaoImpl implements TrainingMaterialDao {

	public static final Logger LOGGER = LoggerFactory.getLogger(TrainingMaterialDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseDto getAllTrainingMaterials() {

		ResponseDto response = new ResponseDto();
		try {
			response.setData(jdbcTemplate.query(Queries.GET_ALL_TRAINING_MATERIAL,
					TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda));
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getData());

		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);

		}
		return response;
	}

	@Override
	public ResponseDto findTrainingMaterialById(int courseId, int instructorId) {

		ResponseDto response = new ResponseDto();
		try {
			response.setData(jdbcTemplate.query(Queries.GET_TRAINING_MATERIAL_BY_IDS,
					TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, courseId, instructorId));
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getData());

		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);

		}
		return response;
	}

	@Override
	public ResponseDto addTrainingMaterial(MultipartFile[] files, int courseId, int instructorId)
			throws IOException, SQLException {

		ResponseDto response = new ResponseDto();
		try {
			int trainingId = jdbcTemplate.queryForObject(Queries.GET_TRAINING_ID_FROM_MATERIAL, Integer.class, courseId,
					instructorId);
			for (MultipartFile file : files) {
				byte[] bytes = file.getBytes();
				Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
				jdbcTemplate.update(Queries.CREATE_TRAINING_MATERIAL, trainingId, blob, file.getOriginalFilename(),
						file.getContentType(), file.getSize());
			}
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getData());

		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);

		}
		return response;

	}

	@Override
	public ResponseDto deleteTrainingMaterial(int id) {

		ResponseDto response = new ResponseDto();
		try {
			response.setAdditionalData(jdbcTemplate.update(Queries.DELETE_TRAINING_MATERIAL, id));
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getAdditionalData());

		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);

		}
		return response;

	}

	public ResponseDto getTrainingVersion(int id) {
		ResponseDto response = new ResponseDto();
		try {
			response.setData(jdbcTemplate.query(Queries.GET_TRAINING_VERSION,
					TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, id));
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getData());

		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);

		}
		return response;
	}

}
