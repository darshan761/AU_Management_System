package com.accolite.aums.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.accolite.aums.models.User;
import com.accolite.aums.service.impl.UserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class AumsApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	   public void testCalculateAppriasal() {
		User user = new User();
		user.setUserName("darshan");
			
	      assertEquals("fvtvt", user.getUserName());
	   }
	
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private UserServiceImpl service;
    
    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
      throws Exception {
    	
    	 
        User alex = new User();
        alex.setUserName("alex");
     
        List<User> allEmployees = Arrays.asList(alex);
    	
    	when(service.getAllUsers()).thenReturn(allEmployees);
    	
		this.mvc.perform(get("/api/user/"))
			.andExpect(status().isOk());
				
        
     
//        given(service.getAllUsers()).willReturn(allEmployees);
//     
//        mvc.perform(get("/api/employees")
//          .contentType(MediaType.APPLICATION_JSON))
//          .andExpect(status().isOk())
//          .andExpect(jsonPath("$", hasSize(1)))
//          .andExpect(jsonPath("$[0].name", is(alex.getUserName())));
    }

}
