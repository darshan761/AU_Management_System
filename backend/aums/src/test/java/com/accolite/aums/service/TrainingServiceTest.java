/**
 * 
 */
package com.accolite.aums.service;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.accolite.aums.dao.impl.TrainingDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.Training;
import com.accolite.aums.service.impl.TrainingServiceImpl;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class TrainingServiceTest {
	
	@Mock
    private TrainingDaoImpl trainingDao;

    @InjectMocks
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
    void findTrainingById() {
 
		response.setData(training);
    	
    	when(trainingDao.findTrainingById(3)).thenReturn(response);
    	int id = 3;
    	
    	ResponseDto responseFound = trainingService.findTrainingById(id);
    	Training trainingFound = (Training) responseFound.getData();
    	assertThat(trainingFound.getTrainingId())
        .isEqualTo(id);
    }
    
    @Test
    void findAllTraining() {

		response.setData(trainingList);
    	
    	when(trainingDao.getAllTrainings()).thenReturn(response);
    	
    	ResponseDto responseFound = trainingService.getAllTrainings();
    	List<Training> trainingFound = (List<Training>) responseFound.getData();
    	assertThat(trainingFound.size())
        .isEqualTo(2);
    }
    
    @Test
    void addTraining() {

		response.setData(training);
		
		when(trainingDao.addTraining(training)).thenReturn(response);
		ResponseDto responseFound = trainingService.addTraining(training);
		Training trainingFound = (Training) responseFound.getData();
		assertThat(responseFound).isNotNull();
		verify(trainingDao).addTraining(trainingFound);
    }
    
	
	@Test
	public void deleteTraining() throws Exception {
		final int trainingId = 1;
		trainingService.deleteTraining(trainingId);
		trainingService.deleteTraining(trainingId);
		
		verify(trainingDao, times(2)).deleteTraining(trainingId);
	}

}
