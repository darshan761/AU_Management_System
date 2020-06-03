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

import com.accolite.aums.dao.impl.TrainingDaoImpl;
import com.accolite.aums.dto.ResponseDto;
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
	
	@Test
    void findTrainingById() {

		Training training = new Training();
		training.setCourseId(1);
		training.setInstructorId(2);
		training.setTrainingId(3);
		training.setFeedback("feedback");
    	
    	when(jdbcTemplate.queryForObject(Queries.GET_TRAINING_BY_ID, TrainingRowMapper.TrainingRowMapperLambda, 3)).thenReturn(training);
    	int id = 3;
    	
    	
    	ResponseDto response = trainingDao.findTrainingById(id);
		Training trainingFound = (Training) response.getData();
		assertEquals(3, trainingFound.getTrainingId());
    	
    	
    }
    
    @Test
    void findAllTraining() {
		Training training1 = new Training();
		training1.setCourseId(1);
		training1.setInstructorId(2);
		training1.setTrainingId(3);
		training1.setFeedback("feedback");
		
		Training training2 = new Training();
		training2.setCourseId(1);
		training2.setInstructorId(2);
		training2.setTrainingId(3);
		training2.setFeedback("feedback");
		
		List<Training> training = new ArrayList<>();
		training.add(training1);
		training.add(training2);
    	
    	when(jdbcTemplate.query(Queries.GET_ALL_TRAINING, TrainingRowMapper.TrainingRowMapperLambda)).thenReturn(training);
    	
    	ResponseDto response = trainingDao.getAllTrainings();
        List<Training> trainings = (List<Training>) response.getData();
        assertEquals(2, trainings.size());
    }
    
    @Test
    void addTraining() {
		Training training = new Training();
		training.setCourseId(1);
		training.setInstructorId(2);
		training.setTrainingId(3);
		training.setFeedback("feedback");
		
		when(jdbcTemplate.update(Queries.CREATE_TRAINING, training.getCourseId(), training.getInstructorId(), training.getFeedback())).thenReturn(1);
		
		ResponseDto responseFound = trainingDao.addTraining(training);
		assertThat(responseFound).isNotNull();
		assertEquals(1, responseFound.getAdditionalData());
    }
    
	
	@Test
	public void deleteTraining() throws Exception {
		
		when(jdbcTemplate.update(Queries.DELETE_TRAINING, 3)).thenReturn(1);
		
		ResponseDto responseFound = trainingDao.deleteTraining(3);
		assertThat(responseFound).isNotNull();
		assertEquals(1, responseFound.getAdditionalData());
	}
}
