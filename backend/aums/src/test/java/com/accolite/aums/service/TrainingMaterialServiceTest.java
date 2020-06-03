/**
 * 
 */
package com.accolite.aums.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import org.springframework.http.MediaType;

import com.accolite.aums.dao.impl.TrainingMaterialDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Training;
import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.service.impl.TrainingMaterialServiceImpl;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class TrainingMaterialServiceTest {
	
	@Mock
    private TrainingMaterialDaoImpl trainingMaterialDao;

    @InjectMocks
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
		
		
		when(trainingMaterialDao.findTrainingMaterialById(1, 3)).thenReturn(response);
		
		int courseId = 1;
		int instructorId = 3;
    	
    	ResponseDto responseFound = trainingMaterialService.findTrainingMaterialById(courseId, instructorId);
    	TrainingMaterial trainingMaterialFound = (TrainingMaterial) responseFound.getData();
    	assertThat(trainingMaterialFound.getFileId())
        .isEqualTo(2);
		
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
		
		when(trainingMaterialDao.getAllTrainingMaterials()).thenReturn(response);
		
		ResponseDto responseFound = trainingMaterialService.getAllTrainingMaterials();
    	List<TrainingMaterial> trainingmaterialFound = (List<TrainingMaterial>) responseFound.getData();
    	assertThat(trainingmaterialFound.size())
        .isEqualTo(2);
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
		final int trainingId = 1;
		trainingMaterialService.deleteTrainingMaterial(trainingId);
		trainingMaterialService.deleteTrainingMaterial(trainingId);
		
		verify(trainingMaterialDao, times(2)).deleteTrainingMaterial(trainingId);
	}
}
