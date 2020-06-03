/**
 * 
 */
package com.accolite.aums.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.aums.dao.impl.UserDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.User;
import com.accolite.aums.queries.Queries;
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
	
	@Test
	public void getUserById() throws Exception {

		User user = new User();
		user.setUserId(1);
		user.setUserDesignation("Designation");
		user.setUserEmail("email");
		user.setUserLocation("location");
				
		when(jdbcTemplate.queryForObject(Queries.GET_USER_BY_ID, UserRowMapper.UserRowMapperLambda, 1)).thenReturn(user);
		    	

		ResponseDto response = userDao.findUserById(1);
        User userFound = (User) response.getData();
        assertEquals(1, userFound.getUserId());
	}
	
	@Test
	public void getAllUser() throws Exception {

		User user = new User();
		user.setUserId(1);
		user.setUserDesignation("Designation");
		user.setUserEmail("email");
		user.setUserLocation("location");
		
		List<User> users = new ArrayList<User>() {{add(user);}};
		
		when(jdbcTemplate.query(Queries.GET_ALL_USERS, UserRowMapper.UserRowMapperLambda)).thenReturn(users);
		    	

		ResponseDto response = userDao.getAllUsers();
         List<User> userFound = (List<User>) response.getData();
        assertEquals(1, userFound.size());
	}

}
