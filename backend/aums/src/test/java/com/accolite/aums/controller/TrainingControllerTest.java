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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Training;
import com.accolite.aums.service.impl.TrainingServiceImpl;
import com.accolite.aums.utils.Utils;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
@AutoConfigureMockMvc
public class TrainingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrainingServiceImpl trainingService;
	
	ResponseDto response = new ResponseDto();
	Training training = new Training();
	Training training2 = new Training();
	List<Training> trainingList = new ArrayList<>();
	
	@BeforeEach
	public void init() {
	
		training.setCourseId(1);
		training.setInstructorId(2);
		training.setTrainingId(3);
		training.setFeedback("feedback");
		
		training2.setCourseId(1);
		training2.setInstructorId(2);
		training2.setTrainingId(3);
		training2.setFeedback("feedback");
		
		trainingList.add(training);
		trainingList.add(training2);
	}

	@Test
	public void getTrainingById() throws Exception {

		response.setData(training);
		
		when(trainingService.findTrainingById(3)).thenReturn(response);
		
		mockMvc.perform(get("/api/training/3")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.feedback", is(training.getFeedback())));
	}
	
	@Test
	public void getAllTraining() throws Exception {
	
		response.setData(trainingList);
		
		when(trainingService.getAllTrainings()).thenReturn(response);
		
		mockMvc.perform(get("/api/training/")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.length()",is(2)));
	}
	
	@Test
	public void addTraining() throws Exception {
		
		response.setData(training);
		
		when(trainingService.addTraining(training)).thenReturn(response);
		
		mockMvc.perform(post("/api/training/add")
				.content(Utils.asJsonString(training))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}
	
	
	@Test
	public void deleteTraining() throws Exception {

		response.setData(training);
		
		when(trainingService.deleteTraining(3)).thenReturn(response);
		mockMvc.perform(delete("/api/training/delete/3")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
}
