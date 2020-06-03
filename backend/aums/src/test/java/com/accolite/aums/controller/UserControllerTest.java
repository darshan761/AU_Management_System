/**
 * 
 */
package com.accolite.aums.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Instructor;
import com.accolite.aums.models.User;
import com.accolite.aums.service.impl.InstructorServiceImpl;
import com.accolite.aums.service.impl.UserServiceImpl;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl userService;

	@Test
	public void getUserById() throws Exception {

		ResponseDto response = new ResponseDto();
		User user = new User();
		user.setUserId(1);
		user.setUserDesignation("Designation");
		user.setUserEmail("email");
		user.setUserLocation("location");
		response.setData(user);
		
		
		when(userService.findUserByEmail("email")).thenReturn(response);
		
		mockMvc.perform(get("/api/user/email")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.userName", is(user.getUserName())));
	}
	
	@Test
	public void getAllUser() throws Exception {

		ResponseDto response = new ResponseDto();
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserDesignation("Designation");
		user1.setUserEmail("email");
		user1.setUserLocation("location");
		
		User user2 = new User();
		user2.setUserId(2);
		user2.setUserDesignation("Designation");
		user2.setUserEmail("email");
		user2.setUserLocation("location");
		
		List<User> user = new ArrayList<>();
		user.add(user1);
		user.add(user2);
		response.setData(user);
		
		when(userService.getAllUsers()).thenReturn(response);
		
		mockMvc.perform(get("/api/user/")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.length()",is(2)));
	}
}
