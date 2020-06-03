/**
 * 
 */
package com.accolite.aums.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.accolite.aums.dao.impl.InstructorDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Instructor;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.InstructorRowMapper;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class InstructorDaoTest {

	@Mock
    private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private InstructorDaoImpl instructorDao;
	
	@Test
	public void getInstructorById() throws Exception {

		Instructor instructor = new Instructor();
		instructor.setInstructorId(1);
		instructor.setCourseId(2);
		instructor.setUserName("Name");
		instructor.setTrainingId(3);
		instructor.setUserDesignation("Designation");
		instructor.setUserEmail("email");
		instructor.setUserLocation("location");
		
		when(jdbcTemplate.queryForObject(Queries.GET_INSTRUCTOR_BY_ID, InstructorRowMapper.InstructorRowMapperLambda, 1)).thenReturn(instructor);
		int id = 1;
    	
    	ResponseDto responseFound = instructorDao.findInstructorById(id);
    	Instructor InstructorFound = (Instructor) responseFound.getData();
    	assertEquals(id, InstructorFound.getInstructorId());
        
		
	}
	
	@Test
	public void getAllInstructor() throws Exception {

		Instructor instructor1 = new Instructor();
		instructor1.setInstructorId(1);
		instructor1.setCourseId(2);
		instructor1.setUserName("Name");
		instructor1.setTrainingId(3);
		instructor1.setUserDesignation("Designation");
		instructor1.setUserEmail("email");
		instructor1.setUserLocation("location");
		
		Instructor instructor2 = new Instructor();
		instructor2.setInstructorId(3);
		instructor2.setCourseId(7);
		instructor2.setUserName("Name");
		instructor2.setTrainingId(8);
		instructor2.setUserDesignation("Designation");
		instructor2.setUserEmail("email");
		instructor2.setUserLocation("location");
		
		List<Instructor> instructor = new ArrayList<>();
		instructor.add(instructor1);
		instructor.add(instructor2);
		
		when(jdbcTemplate.query(Queries.GET_ALL_INSTRUCTORS, InstructorRowMapper.InstructorRowMapperLambda)).thenReturn(instructor);
		
		ResponseDto responseFound = instructorDao.getAllInstructors();
    	List<Instructor> instructorFound = (List<Instructor>) responseFound.getData();
    	assertEquals(2, instructorFound.size());
        
		
	}
	
	@Test
	public void getInstructorByCourse() throws Exception {

		Instructor instructor = new Instructor();
		instructor.setInstructorId(1);
		instructor.setCourseId(2);
		instructor.setUserName("Name");
		instructor.setTrainingId(3);
		instructor.setUserDesignation("Designation");
		instructor.setUserEmail("email");
		instructor.setUserLocation("location");
		
		List<Instructor> instructorList = new ArrayList<>();
		instructorList.add(instructor);
		
		when(jdbcTemplate.query(Queries.GET_INSTRUCTORS_BY_COURSE, InstructorRowMapper.InstructorRowMapperLambda, 2)).thenReturn(instructorList);
		int id = 2;
		
		ResponseDto responseFound = instructorDao.findInstructorByCourseId(id);
		List<Instructor> InstructorFound = (List<Instructor>) responseFound.getData();
		assertThat(responseFound).isNotNull();
		assertEquals(1, InstructorFound.size());
       
	}
	
	
	@Test
	public void deleteInstructor() throws Exception {
		when(jdbcTemplate.update(Queries.DELETE_INSTRUCTOR, 2)).thenReturn(1);
		int id = 2;
		
		ResponseDto responseFound = instructorDao.deleteInstructor(id);
		assertThat(responseFound).isNotNull();
		assertEquals(1, responseFound.getAdditionalData());
		
	}

	
}
