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
    
    @Test
    void findTrainingById() {
    	ResponseDto response = new ResponseDto();
		Training training = new Training();
		training.setCourseId(1);
		training.setInstructorId(2);
		training.setTrainingId(3);
		training.setFeedback("feedback");
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
    	ResponseDto response = new ResponseDto();
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
		response.setData(training);
    	
    	when(trainingDao.getAllTrainings()).thenReturn(response);
    	
    	ResponseDto responseFound = trainingService.getAllTrainings();
    	List<Training> trainingFound = (List<Training>) responseFound.getData();
    	assertThat(trainingFound.size())
        .isEqualTo(2);
    }
    
    @Test
    void addTraining() {
    	ResponseDto response = new ResponseDto();
		Training training = new Training();
		training.setCourseId(1);
		training.setInstructorId(2);
		training.setTrainingId(3);
		training.setFeedback("feedback");
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
