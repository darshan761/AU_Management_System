/**
 * 
 */
package com.accolite.aums.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.service.impl.TrainingMaterialServiceImpl;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
@AutoConfigureMockMvc
public class TrainingMaterialControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrainingMaterialServiceImpl trainingMaterialService;

	@Test
	public void getTrainingMaterialById() throws Exception {

		ResponseDto response = new ResponseDto();
		TrainingMaterial trainingMaterial = new TrainingMaterial();
		trainingMaterial.setCourseId(1);
		trainingMaterial.setFileId(2);
		trainingMaterial.setFileName("file");
		trainingMaterial.setFileType("pdf");
		trainingMaterial.setInstructorId(3);
		response.setData(trainingMaterial);
		
		
		when(trainingMaterialService.findTrainingMaterialById(1, 3)).thenReturn(response);
		
		mockMvc.perform(get("/api/training/material/1/3")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.fileName", is(trainingMaterial.getFileName())));
	}
	
	@Test
	public void getAllTrainingMaterial() throws Exception {

		ResponseDto response = new ResponseDto();
		TrainingMaterial trainingMaterial1 = new TrainingMaterial();
		trainingMaterial1.setCourseId(1);
		trainingMaterial1.setFileId(2);
		trainingMaterial1.setFileName("file");
		trainingMaterial1.setFileType("pdf");
		trainingMaterial1.setInstructorId(3);
		
		TrainingMaterial trainingMaterial2 = new TrainingMaterial();
		trainingMaterial2.setCourseId(2);
		trainingMaterial2.setFileId(4);
		trainingMaterial2.setFileName("file");
		trainingMaterial2.setFileType("pdf");
		trainingMaterial2.setInstructorId(8);
		
		List<TrainingMaterial> trainingMaterial = new ArrayList<>();
		trainingMaterial.add(trainingMaterial1);
		trainingMaterial.add(trainingMaterial2);
		response.setData(trainingMaterial);
		
		when(trainingMaterialService.getAllTrainingMaterials()).thenReturn(response);
		
		mockMvc.perform(get("/api/training/material/")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.length()",is(2)));
	}
	
//	@Test
//	public void addTrainingMaterial() throws Exception {
//
//		ResponseDto response = new ResponseDto();
//		TrainingMaterial trainingMaterial = new TrainingMaterial();
//		trainingMaterial.setCourseId(1);
//		trainingMaterial.setFileId(2);
//		trainingMaterial.setFileName("file");
//		trainingMaterial.setFileType("pdf");
//		trainingMaterial.setInstructorId(3);
//		response.setData(trainingMaterial);
//		MultipartFile[] file = null;
//		
//		when(trainingMaterialService.addTrainingMaterial(file,trainingMaterial.getCourseId(),trainingMaterial.getInstructorId())).thenReturn(response);
//		
//		mockMvc.perform(post("/api/training/material/add")
//				.content(Utils.asJsonString(trainingMaterial))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andReturn();
//	}
//	
	
	@Test
	public void deleteTrainingMaterial() throws Exception {
		ResponseDto response = new ResponseDto();
		TrainingMaterial trainingMaterial = new TrainingMaterial();
		trainingMaterial.setTrainingId(11);
		trainingMaterial.setCourseId(1);
		trainingMaterial.setFileId(2);
		trainingMaterial.setFileName("file");
		trainingMaterial.setFileType("pdf");
		trainingMaterial.setInstructorId(3);
		response.setData(trainingMaterial);
		
		when(trainingMaterialService.deleteTrainingMaterial(11)).thenReturn(response);
		mockMvc.perform(delete("/api/training/delete/3")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andReturn();
	}
}
