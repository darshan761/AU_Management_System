/**

 * 
 */

package com.accolite.aums.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.aums.dao.impl.TrainingMaterialDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.models.Training;
import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.TrainingMaterialRowMapper;
import com.accolite.aums.rowmapper.TrainingRowMapper;

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
	
		when(jdbcTemplate.query(Queries.GET_TRAINING_MATERIAL_BY_IDS,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, trainingMaterial.getCourseId(), trainingMaterial.getInstructorId())).thenReturn(trainingMaterialList);
		
		int courseId = 1;
		int instructorId = 3;
    	
    	response = trainingMaterialDao.findTrainingMaterialById(courseId, instructorId);
    	List<TrainingMaterial> trainingMaterialFound = (List<TrainingMaterial>) response.getData();
    	assertEquals(2, trainingMaterialFound.size());
       
    	doThrow(new RuntimeException()).when(jdbcTemplate).query(Queries.GET_TRAINING_MATERIAL_BY_IDS,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, trainingMaterial.getCourseId(), trainingMaterial.getInstructorId());

    	response = trainingMaterialDao.findTrainingMaterialById(courseId, instructorId);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).query(Queries.GET_TRAINING_MATERIAL_BY_IDS,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, trainingMaterial.getCourseId(), trainingMaterial.getInstructorId());
    	
		
	}
	
	@Test
	public void getAllTrainingMaterial() throws Exception {
		
		when(jdbcTemplate.query(Queries.GET_ALL_TRAINING_MATERIAL,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda)).thenReturn(trainingMaterialList);
		
		response = trainingMaterialDao.getAllTrainingMaterials();
    	List<TrainingMaterial> trainingmaterialFound = (List<TrainingMaterial>) response.getData();
    	assertEquals(2, trainingmaterialFound.size());
       
    	doThrow(new RuntimeException()).when(jdbcTemplate).query(Queries.GET_ALL_TRAINING_MATERIAL,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda);

		response = trainingMaterialDao.getAllTrainingMaterials();

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).query(Queries.GET_ALL_TRAINING_MATERIAL,
				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda);
    	
    	
	}
	
//	@Test
//	public void addTrainingMaterial() throws Exception {
//
//		
//		when(jdbcTemplate.query(Queries.GET_ALL_TRAINING_MATERIAL,
//				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda)).thenReturn(trainingMaterialList);
//		
//		response = trainingMaterialDao.getAllTrainingMaterials();
//    	List<TrainingMaterial> trainingmaterialFound = (List<TrainingMaterial>) response.getData();
//    	assertEquals(2, trainingmaterialFound.size());
//       
//    	doThrow(new RuntimeException()).when(jdbcTemplate).query(Queries.GET_ALL_TRAINING_MATERIAL,
//				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda);
//
//		response = trainingMaterialDao.getAllTrainingMaterials();
//
//		assertEquals(ResponseType.FAILURE, response.getResponseType());
//
//		verify(jdbcTemplate, times(2)).query(Queries.GET_ALL_TRAINING_MATERIAL,
//				TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda);
//	}
	
	
	@Test
	public void deleteTrainingMaterial() throws Exception {
		when(jdbcTemplate.update(Queries.DELETE_TRAINING_MATERIAL, 3)).thenReturn(1);
		
		response = trainingMaterialDao.deleteTrainingMaterial(3);
		assertThat(response).isNotNull();
		assertEquals(1, response.getAdditionalData());
		
		doThrow(new RuntimeException()).when(jdbcTemplate).update(Queries.DELETE_TRAINING_MATERIAL, 3);

		response = trainingMaterialDao.deleteTrainingMaterial(3);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).update(Queries.DELETE_TRAINING_MATERIAL, 3);
	}

}
