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
import org.springframework.web.multipart.MultipartFile;

import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Email;
import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.service.impl.EmailServiceImpl;
import com.accolite.aums.service.impl.TrainingMaterialServiceImpl;
import com.accolite.aums.utils.Utils;

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
	
	@MockBean
	private EmailServiceImpl emailService;
	
	ResponseDto response = new ResponseDto();
	TrainingMaterial trainingMaterial = new TrainingMaterial();
	TrainingMaterial trainingMaterial2 = new TrainingMaterial();
	List<TrainingMaterial> trainingMaterialList = new ArrayList<>();

	@BeforeEach
	public void init() {
		trainingMaterial.setCourseId(1);
		trainingMaterial.setFileId(2);
		trainingMaterial.setFileName("file");
		trainingMaterial.setFileType("pdf");
		trainingMaterial.setInstructorId(3);
		
		trainingMaterial2.setCourseId(2);
		trainingMaterial2.setFileId(4);
		trainingMaterial2.setFileName("file");
		trainingMaterial2.setFileType("pdf");
		trainingMaterial2.setInstructorId(8);
		
		trainingMaterialList.add(trainingMaterial);
		trainingMaterialList.add(trainingMaterial2);
	}
	
	@Test
	public void getTrainingMaterialById() throws Exception {
		
		response.setData(trainingMaterial);	
		
		when(trainingMaterialService.findTrainingMaterialById(1, 3)).thenReturn(response);
		
		mockMvc.perform(get("/api/training/material/1/3")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.fileName", is(trainingMaterial.getFileName())));
	}
	
	@Test
	public void getAllTrainingMaterial() throws Exception {
		
		response.setData(trainingMaterialList);
		
		when(trainingMaterialService.getAllTrainingMaterials()).thenReturn(response);
		
		mockMvc.perform(get("/api/training/material/")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.length()",is(2)));
	}
	
	@Test
	public void addTrainingMaterial() throws Exception {

		response.setData(trainingMaterial);
		MultipartFile[] file = null;
		
		when(trainingMaterialService.addTrainingMaterial(file,trainingMaterial.getCourseId(),trainingMaterial.getInstructorId())).thenReturn(response);
		
		mockMvc.perform(post("/api/training/material/add?courseId=3&instructorId=1")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.MULTIPART_FORM_DATA))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}
	
	
	@Test
	public void deleteTrainingMaterial() throws Exception {
		
		response.setData(trainingMaterial);
		
		when(trainingMaterialService.deleteTrainingMaterial(11)).thenReturn(response);
		mockMvc.perform(delete("/api/training/material/delete/3")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void getTrainingVersion() throws Exception {
		
		response.setData(trainingMaterial);
		
		when(trainingMaterialService.getTrainingVersion(1)).thenReturn(response);
		mockMvc.perform(get("/api/training/material/version/1")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void sendEmail() throws Exception {
		Email email = new Email();
		email.setMessage("message");
		response.setAdditionalData(1);
		when(emailService.sendEmail(email)).thenReturn(response);
		mockMvc.perform(post("/api/training/material/mail/")
				.content(Utils.asJsonString(email))
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andReturn();
	}
}
