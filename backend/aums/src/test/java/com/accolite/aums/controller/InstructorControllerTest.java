/**
 * 
 */
package com.accolite.aums.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Instructor;
import com.accolite.aums.models.Training;
import com.accolite.aums.service.impl.InstructorServiceImpl;
import com.accolite.aums.utils.Utils;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
@AutoConfigureMockMvc
public class InstructorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InstructorServiceImpl instructorService;

	@Test
	public void getInstructorById() throws Exception {

		ResponseDto response = new ResponseDto();
		Instructor instructor = new Instructor();
		instructor.setInstructorId(1);
		instructor.setCourseId(2);
		instructor.setUserName("Name");
		instructor.setTrainingId(3);
		instructor.setUserDesignation("Designation");
		instructor.setUserEmail("email");
		instructor.setUserLocation("location");
		response.setData(instructor);
		
		
		when(instructorService.findInstructorById(1)).thenReturn(response);
		
		mockMvc.perform(get("/api/instructor/1")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.userName", is(instructor.getUserName())));
	}
	
	@Test
	public void getAllInstructor() throws Exception {

		ResponseDto response = new ResponseDto();
		Instructor instructor1 = new Instructor();
		instructor1.setInstructorId(1);
		instructor1.setCourseId(2);
		instructor1.setUserName("Name");
		instructor1.setTrainingId(3);
		instructor1.setUserDesignation("Designation");
		instructor1.setUserEmail("email");
		instructor1.setUserLocation("location");
		
		Instructor instructor2 = new Instructor();
		instructor2.setInstructorId(3);
		instructor2.setCourseId(7);
		instructor2.setUserName("Name");
		instructor2.setTrainingId(8);
		instructor2.setUserDesignation("Designation");
		instructor2.setUserEmail("email");
		instructor2.setUserLocation("location");
		
		List<Instructor> instructor = new ArrayList<>();
		instructor.add(instructor1);
		instructor.add(instructor2);
		response.setData(instructor);
		
		when(instructorService.getAllInstructors()).thenReturn(response);
		
		mockMvc.perform(get("/api/instructor/")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.length()",is(2)));
	}
	
	@Test
	public void getInstructorByCourse() throws Exception {

		ResponseDto response = new ResponseDto();
		Instructor instructor = new Instructor();
		instructor.setInstructorId(1);
		instructor.setCourseId(2);
		instructor.setUserName("Name");
		instructor.setTrainingId(3);
		instructor.setUserDesignation("Designation");
		instructor.setUserEmail("email");
		instructor.setUserLocation("location");
		response.setData(instructor);
		
		when(instructorService.findInstructorByCourseId(2)).thenReturn(response);
		
		mockMvc.perform(get("/api/instructor/course/2"))
				 .andExpect(status().isOk())
				 .andExpect(jsonPath("$.data.userName",is(instructor.getUserName())));
	}
	
	
	@Test
	public void deleteInstructor() throws Exception {
		ResponseDto response = new ResponseDto();
		Instructor instructor = new Instructor();
		instructor.setInstructorId(1);
		instructor.setCourseId(2);
		instructor.setUserName("Name");
		instructor.setTrainingId(3);
		instructor.setUserDesignation("Designation");
		instructor.setUserEmail("email");
		instructor.setUserLocation("location");
		response.setData(instructor);
		
		when(instructorService.deleteInstructor(1)).thenReturn(response);
		mockMvc.perform(delete("/api/instructor/delete/1")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andReturn();
	}
}
