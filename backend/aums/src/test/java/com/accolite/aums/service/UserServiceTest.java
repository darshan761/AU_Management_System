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
	
	@Test
	public void getUserById() throws Exception {

		ResponseDto response = new ResponseDto();
		User user = new User();
		user.setUserId(1);
		user.setUserDesignation("Designation");
		user.setUserEmail("email");
		user.setUserLocation("location");
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
		
		when(userDao.getAllUsers()).thenReturn(response);
	
		ResponseDto responseFound = userService.getAllUsers();
    	List<User> userFound = (List<User>) responseFound.getData();
    	assertThat(userFound.size())
        .isEqualTo(2);
	}

}
