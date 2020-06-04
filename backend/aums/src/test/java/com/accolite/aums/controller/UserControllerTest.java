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

import org.junit.jupiter.api.BeforeEach;
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
	
	ResponseDto response = new ResponseDto();
	User user = new User();
	User user2 = new User();
	List<User> userList = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		
		user.setUserId(1);
		user.setUserDesignation("Designation");
		user.setUserEmail("email");
		user.setUserLocation("location");
		
		user2.setUserId(2);
		user2.setUserDesignation("Designation");
		user2.setUserEmail("email");
		user2.setUserLocation("location");
		
		userList.add(user);
		userList.add(user2);
	}

	@Test
	public void getUserByEmail() throws Exception {
		
		response.setData(user);
		
		when(userService.findUserByEmail("email")).thenReturn(response);
		
		mockMvc.perform(get("/api/user/email")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.userName", is(user.getUserName())));
	}
	
	@Test
	public void getAllUser() throws Exception {
			
		response.setData(userList);
		
		when(userService.getAllUsers()).thenReturn(response);
		
		mockMvc.perform(get("/api/user/")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.length()",is(2)));
	}
}
