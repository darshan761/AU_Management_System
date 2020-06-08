/**
 * 
 */
package com.accolite.aums.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accolite.aums.dao.UserDao;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.UserRowMapper;

/**
 * @author darshan
 *
 */
@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseDto getAllUsers() {

		ResponseDto response = new ResponseDto();
		try {
			response.setData(jdbcTemplate.query(Queries.GET_ALL_USERS, UserRowMapper.UserRowMapperLambda));
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
	public ResponseDto findUserByEmail(String email) {
		ResponseDto response = new ResponseDto();
		try {
			response.setData(
					jdbcTemplate.queryForObject(Queries.GET_USER_BY_EMAIL, UserRowMapper.UserRowMapperLambda, email));
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
