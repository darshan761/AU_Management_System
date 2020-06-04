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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.accolite.aums.dao.impl.InstructorDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.Instructor;
import com.accolite.aums.service.impl.InstructorServiceImpl;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class InstructorServiceTest {
	
	@Mock
    private InstructorDaoImpl instructorDao;

    @InjectMocks
    private InstructorServiceImpl instructorService;
	
    ResponseDto response = new ResponseDto();
	Instructor instructor = new Instructor();
	Instructor instructor2 = new Instructor();
	List<Instructor> instructorList = new ArrayList<>();

	@BeforeEach
	public void init() {

		instructor.setInstructorId(1);
		instructor.setCourseId(2);
		instructor.setUserName("Name");
		instructor.setTrainingId(3);
		instructor.setUserDesignation("Designation");
		instructor.setUserEmail("email");
		instructor.setUserLocation("location");
		
		instructor2.setInstructorId(3);
		instructor2.setCourseId(7);
		instructor2.setUserName("Name");
		instructor2.setTrainingId(8);
		instructor2.setUserDesignation("Designation");
		instructor2.setUserEmail("email");
		instructor2.setUserLocation("location");
		
		instructorList.add(instructor);
		instructorList.add(instructor2);
	}
    
	@Test
	public void getInstructorById() throws Exception {

		response.setData(instructor);	
		
		when(instructorService.findInstructorById(1)).thenReturn(response);
		int id = 1;
    	
    	ResponseDto responseFound = instructorService.findInstructorById(id);
    	Instructor InstructorFound = (Instructor) responseFound.getData();
    	assertThat(InstructorFound.getInstructorId())
        .isEqualTo(id);
		
	}
	
	@Test
	public void getAllInstructor() throws Exception {

		response.setData(instructorList);
		
		when(instructorDao.getAllInstructors()).thenReturn(response);
		
		ResponseDto responseFound = instructorService.getAllInstructors();
    	List<Instructor> instructorFound = (List<Instructor>) responseFound.getData();
    	assertThat(instructorFound.size())
        .isEqualTo(2);
		
	}
	
	@Test
	public void getInstructorByCourse() throws Exception {
		response.setData(instructor);
		
		when(instructorDao.findInstructorByCourseId(2)).thenReturn(response);
		int id = 2;
		
		ResponseDto responseFound = instructorService.findInstructorByCourseId(id);
		Instructor InstructorFound = (Instructor) responseFound.getData();
		assertThat(responseFound).isNotNull();
		assertThat(InstructorFound.getCourseId())
        .isEqualTo(id);
	}
	
	
	@Test
	public void deleteInstructor() throws Exception {
		final int instructorId = 1;
		instructorService.deleteInstructor(instructorId);
		instructorService.deleteInstructor(instructorId);
		
		verify(instructorDao, times(2)).deleteInstructor(instructorId);
	}

}
