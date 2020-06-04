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

import com.accolite.aums.dao.impl.TrainingDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.Training;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.TrainingRowMapper;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class TrainingDaoTest {
	
	@Mock
    private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private TrainingDaoImpl trainingDao;
	
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
    void findTrainingById() {
    	
    	when(jdbcTemplate.queryForObject(Queries.GET_TRAINING_BY_ID, TrainingRowMapper.TrainingRowMapperLambda, 3)).thenReturn(training);
    	int id = 3;
    	
    	
    	response = trainingDao.findTrainingById(id);
		Training trainingFound = (Training) response.getData();
		assertEquals(3, trainingFound.getTrainingId());
		
		doThrow(new RuntimeException()).when(jdbcTemplate).queryForObject(Queries.GET_TRAINING_BY_ID, TrainingRowMapper.TrainingRowMapperLambda, 3);

    	response = trainingDao.findTrainingById(id);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).queryForObject(Queries.GET_TRAINING_BY_ID, TrainingRowMapper.TrainingRowMapperLambda, 3);
  	
    }
    
    @Test
    void findAllTraining() {

    	when(jdbcTemplate.query(Queries.GET_ALL_TRAINING, TrainingRowMapper.TrainingRowMapperLambda)).thenReturn(trainingList);
    	
    	response = trainingDao.getAllTrainings();
        List<Training> trainings = (List<Training>) response.getData();
        assertEquals(2, trainings.size());
        
        doThrow(new RuntimeException()).when(jdbcTemplate).query(Queries.GET_ALL_TRAINING, TrainingRowMapper.TrainingRowMapperLambda);

    	response = trainingDao.getAllTrainings();

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).query(Queries.GET_ALL_TRAINING, TrainingRowMapper.TrainingRowMapperLambda);
    }
    
    @Test
    void addTraining() {

		when(jdbcTemplate.update(Queries.CREATE_TRAINING, training.getCourseId(), training.getInstructorId(), training.getFeedback())).thenReturn(1);
		
		response = trainingDao.addTraining(training);
		assertThat(response).isNotNull();
		assertEquals(1, response.getAdditionalData());
		
		doThrow(new RuntimeException()).when(jdbcTemplate).update(Queries.CREATE_TRAINING, training.getCourseId(), training.getInstructorId(), training.getFeedback());

		response = trainingDao.addTraining(training);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).update(Queries.CREATE_TRAINING, training.getCourseId(), training.getInstructorId(), training.getFeedback());
    }
    
	
	@Test
	public void deleteTraining() throws Exception {
		
		when(jdbcTemplate.update(Queries.DELETE_TRAINING, 3)).thenReturn(1);
		
		response = trainingDao.deleteTraining(3);
		assertThat(response).isNotNull();
		assertEquals(1, response.getAdditionalData());
		
		doThrow(new RuntimeException()).when(jdbcTemplate).update(Queries.DELETE_TRAINING, 3);

		response = trainingDao.deleteTraining(3);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).update(Queries.DELETE_TRAINING, 3);
	}
}
