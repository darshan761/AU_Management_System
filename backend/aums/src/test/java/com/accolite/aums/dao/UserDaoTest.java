/**
 * 
 */
package com.accolite.aums.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.aums.dao.impl.UserDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.User;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.TrainingRowMapper;
import com.accolite.aums.rowmapper.UserRowMapper;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class UserDaoTest {
	
	@Mock
    private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private UserDaoImpl userDao;
	
	ResponseDto response = new ResponseDto();
	User user = new User();
	User user2 = new User();
	List<User> userList = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		
		user.setUserId(1);
		user.setUserDesignation("Designation");
		user.setUserEmail("email1");
		user.setUserLocation("location");
		
		user2.setUserId(2);
		user2.setUserDesignation("Designation");
		user2.setUserEmail("email");
		user2.setUserLocation("location");
		
		userList.add(user);
		userList.add(user2);
	}
	
	@Test
	public void getUserById() throws Exception {
				
		when(jdbcTemplate.queryForObject(Queries.GET_USER_BY_EMAIL, UserRowMapper.UserRowMapperLambda, "email1")).thenReturn(user);
		    	

		response = userDao.findUserByEmail("email1");
        User userFound = (User) response.getData();
        assertEquals(1, userFound.getUserId());
        
        doThrow(new RuntimeException()).when(jdbcTemplate).queryForObject(Queries.GET_USER_BY_EMAIL, UserRowMapper.UserRowMapperLambda, "email1");

		response = userDao.findUserByEmail("email1");

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).queryForObject(Queries.GET_USER_BY_EMAIL, UserRowMapper.UserRowMapperLambda, "email1");
    	
	}
	
	@Test
	public void getAllUser() throws Exception {
		
		when(jdbcTemplate.query(Queries.GET_ALL_USERS, UserRowMapper.UserRowMapperLambda)).thenReturn(userList);

		 response = userDao.getAllUsers();
         List<User> userFound = (List<User>) response.getData();
        assertEquals(2, userFound.size());
        
        doThrow(new RuntimeException()).when(jdbcTemplate).query(Queries.GET_ALL_USERS, UserRowMapper.UserRowMapperLambda);

		 response = userDao.getAllUsers();

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).query(Queries.GET_ALL_USERS, UserRowMapper.UserRowMapperLambda);
	}

}
