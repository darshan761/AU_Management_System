/**
 * 
 */
package com.accolite.aums.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.accolite.aums.dao.impl.UserDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Training;
import com.accolite.aums.models.User;
import com.accolite.aums.service.impl.UserServiceImpl;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class UserServiceTest {
	
	@Mock
    private UserDaoImpl userDao;

    @InjectMocks
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
		
		
		when(userDao.findUserByEmail("email")).thenReturn(response);
		
		String email = "email";
    	
    	ResponseDto responseFound = userService.findUserByEmail(email);
    	User userFound = (User) responseFound.getData();
    	assertThat(userFound.getUserId())
        .isEqualTo(1);	
	}
	
	@Test
	public void getAllUser() throws Exception {

		response.setData(userList);
		
		when(userDao.getAllUsers()).thenReturn(response);
	
		ResponseDto responseFound = userService.getAllUsers();
    	List<User> userFound = (List<User>) responseFound.getData();
    	assertThat(userFound.size())
        .isEqualTo(2);
	}

}
