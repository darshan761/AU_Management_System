/**
 * 
 */
package com.accolite.aums.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.aums.dao.impl.TrainingMaterialDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.TrainingMaterial;
import com.accolite.aums.service.impl.TrainingMaterialServiceImpl;
import com.accolite.aums.utils.Utils;

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
		
		response.setData(trainingMaterialList);
		
		when(trainingMaterialDao.getAllTrainingMaterials()).thenReturn(response);
		
		ResponseDto responseFound = trainingMaterialService.getAllTrainingMaterials();
    	List<TrainingMaterial> trainingmaterialFound = (List<TrainingMaterial>) responseFound.getData();
    	assertThat(trainingmaterialFound.size())
        .isEqualTo(2);
	}
	
	@Test
	public void addTrainingMaterial() throws Exception {

response.setData(trainingMaterialList);
		
		MultipartFile[] file = null;
		response.setAdditionalData(1);
		when(trainingMaterialDao.addTrainingMaterial(file, 1, 2)).thenReturn(response);
		
		ResponseDto responseFound = trainingMaterialService.addTrainingMaterial(file, 1, 2);
    	assertThat(responseFound.getAdditionalData())
        .isEqualTo(1);

	}
	
	
	@Test
	public void deleteTrainingMaterial() throws Exception {
		final int trainingId = 1;
		trainingMaterialService.deleteTrainingMaterial(trainingId);
		trainingMaterialService.deleteTrainingMaterial(trainingId);
		
		verify(trainingMaterialDao, times(2)).deleteTrainingMaterial(trainingId);
	}
	
	@Test
	public void getTrainingVersion() throws Exception {
		response.setData(trainingMaterial);
		
		when(trainingMaterialDao.getTrainingVersion(1)).thenReturn(response);
		
		int courseId = 1;
    	
    	ResponseDto responseFound = trainingMaterialService.getTrainingVersion(1);
    	TrainingMaterial trainingMaterialFound = (TrainingMaterial) responseFound.getData();
    	assertThat(trainingMaterialFound.getFileId())
        .isEqualTo(2);
	}
}
