/**

 * 
 */

package com.accolite.aums.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.aums.dao.impl.TrainingMaterialDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Training;
import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.TrainingMaterialRowMapper;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class TrainingMaterialDaoTest {
	
	@Mock
    private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private TrainingMaterialDaoImpl trainingMaterialDao;
	
	@Test
	public void getTrainingMaterialById() throws Exception {

		TrainingMaterial trainingMaterial = new TrainingMaterial();
		trainingMaterial.setCourseId(1);
		trainingMaterial.setFileId(2);
		trainingMaterial.setFileName("file");
		trainingMaterial.setFileType("pdf");
		trainingMaterial.setInstructorId(3);
		List<TrainingMaterial> trainingList = new ArrayList<TrainingMaterial>() {{ add(trainingMaterial);}};
		
		when(jdbcTemplate.query(Queries.GET_TRAINING_MATERIAL_BY_IDS,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, trainingMaterial.getCourseId(), trainingMaterial.getInstructorId())).thenReturn(trainingList);
		
		int courseId = 1;
		int instructorId = 3;
    	
    	ResponseDto responseFound = trainingMaterialDao.findTrainingMaterialById(courseId, instructorId);
    	List<TrainingMaterial> trainingMaterialFound = (List<TrainingMaterial>) responseFound.getData();
    	assertEquals(1, trainingMaterialFound.size());
       
		
	}
	
	@Test
	public void getAllTrainingMaterial() throws Exception {

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
		
		when(jdbcTemplate.query(Queries.GET_ALL_TRAINING_MATERIAL,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda)).thenReturn(trainingMaterial);
		
		ResponseDto responseFound = trainingMaterialDao.getAllTrainingMaterials();
    	List<TrainingMaterial> trainingmaterialFound = (List<TrainingMaterial>) responseFound.getData();
    	assertEquals(2, trainingmaterialFound.size());
       
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
		when(jdbcTemplate.update(Queries.DELETE_TRAINING_MATERIAL, 3)).thenReturn(1);
		
		ResponseDto responseFound = trainingMaterialDao.deleteTrainingMaterial(3);
		assertThat(responseFound).isNotNull();
		assertEquals(1, responseFound.getAdditionalData());
	}

}
