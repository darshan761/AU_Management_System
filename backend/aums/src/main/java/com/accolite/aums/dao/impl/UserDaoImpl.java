/**
 * 
 */
package com.accolite.aums.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accolite.aums.dao.UserDao;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.models.User;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.CourseRowMapper;
import com.accolite.aums.rowmapper.UserRowMapper;

/**
 * @author darshan
 *
 */
@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseDto getAllUsers() {
		
		ResponseDto response = new ResponseDto();		
		try {
			response.setData(jdbcTemplate.query(Queries.GET_ALL_USERS, UserRowMapper.UserRowMapperLambda));
			response.setResponseType(ResponseType.SUCCESS);
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
		}
		return response;
	}


	@Override
	public ResponseDto findUserByEmail(String email) {
		ResponseDto response = new ResponseDto();		
		try {
			response.setData(jdbcTemplate.queryForObject(Queries.GET_USER_BY_EMAIL, UserRowMapper.UserRowMapperLambda, email));
			response.setResponseType(ResponseType.SUCCESS);
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
		}
		return response;
 
	}

}
