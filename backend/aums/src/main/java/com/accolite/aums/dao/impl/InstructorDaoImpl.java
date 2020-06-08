/**
 * 
 */
package com.accolite.aums.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.aums.dao.InstructorDao;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.InstructorRowMapper;

/**
 * @author darshan
 *
 */
@Repository
public class InstructorDaoImpl implements InstructorDao {

	public static final Logger LOGGER = LoggerFactory.getLogger(InstructorDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseDto getAllInstructors() {
		ResponseDto response = new ResponseDto();
		try {
			response.setData(
					jdbcTemplate.query(Queries.GET_ALL_INSTRUCTORS, InstructorRowMapper.InstructorRowMapperLambda));
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
	public ResponseDto findInstructorById(int id) {
		ResponseDto response = new ResponseDto();
		try {
			response.setData(jdbcTemplate.queryForObject(Queries.GET_INSTRUCTOR_BY_ID,
					InstructorRowMapper.InstructorRowMapperLambda, id));
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
	public ResponseDto deleteInstructor(int id) {
		ResponseDto response = new ResponseDto();
		try {
			response.setAdditionalData(jdbcTemplate.update(Queries.DELETE_INSTRUCTOR, id));
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getAdditionalData());

		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);

		}
		return response;

	}

	@Override
	public ResponseDto findInstructorByCourseId(int id) {
		ResponseDto response = new ResponseDto();
		try {
			response.setData(jdbcTemplate.query(Queries.GET_INSTRUCTORS_BY_COURSE,
					InstructorRowMapper.InstructorRowMapperLambda, id));
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
