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

import com.accolite.aums.dao.impl.InstructorDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.models.Instructor;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.CourseRowMapper;
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
		
		when(jdbcTemplate.queryForObject(Queries.GET_INSTRUCTOR_BY_ID, InstructorRowMapper.InstructorRowMapperLambda, 1)).thenReturn(instructor);
		int id = 1;
    	
    	response = instructorDao.findInstructorById(id);
    	Instructor InstructorFound = (Instructor) response.getData();
    	assertEquals(id, InstructorFound.getInstructorId());
        
    	doThrow(new RuntimeException()).when(jdbcTemplate).queryForObject(Queries.GET_INSTRUCTOR_BY_ID, InstructorRowMapper.InstructorRowMapperLambda, 1);

    	response = instructorDao.findInstructorById(id);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).queryForObject(Queries.GET_INSTRUCTOR_BY_ID, InstructorRowMapper.InstructorRowMapperLambda, 1);
	}
	
	@Test
	public void getAllInstructor() throws Exception {
		
		when(jdbcTemplate.query(Queries.GET_ALL_INSTRUCTORS, InstructorRowMapper.InstructorRowMapperLambda)).thenReturn(instructorList);
		
		response = instructorDao.getAllInstructors();
    	List<Instructor> instructorFound = (List<Instructor>) response.getData();
    	assertEquals(2, instructorFound.size());
        
    	doThrow(new RuntimeException()).when(jdbcTemplate).query(Queries.GET_ALL_INSTRUCTORS, InstructorRowMapper.InstructorRowMapperLambda);

		response = instructorDao.getAllInstructors();

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).query(Queries.GET_ALL_INSTRUCTORS, InstructorRowMapper.InstructorRowMapperLambda);
		
	}
	
	@Test
	public void getInstructorByCourse() throws Exception {
		
		when(jdbcTemplate.query(Queries.GET_INSTRUCTORS_BY_COURSE, InstructorRowMapper.InstructorRowMapperLambda, 2)).thenReturn(instructorList);
		int id = 2;
		
		response = instructorDao.findInstructorByCourseId(id);
		List<Instructor> InstructorFound = (List<Instructor>) response.getData();
		assertThat(response).isNotNull();
		assertEquals(2, InstructorFound.size());
		
		doThrow(new RuntimeException()).when(jdbcTemplate).query(Queries.GET_INSTRUCTORS_BY_COURSE, InstructorRowMapper.InstructorRowMapperLambda, 2);

		response = instructorDao.findInstructorByCourseId(id);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).query(Queries.GET_INSTRUCTORS_BY_COURSE, InstructorRowMapper.InstructorRowMapperLambda, 2);
       
	}
	
	
	@Test
	public void deleteInstructor() throws Exception {
		when(jdbcTemplate.update(Queries.DELETE_INSTRUCTOR, 2)).thenReturn(1);
		int id = 2;
		
		response = instructorDao.deleteInstructor(id);
		assertThat(response).isNotNull();
		assertEquals(1, response.getAdditionalData());
		
		doThrow(new RuntimeException()).when(jdbcTemplate).update(Queries.DELETE_INSTRUCTOR, 2);

		response = instructorDao.deleteInstructor(id);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).update(Queries.DELETE_INSTRUCTOR, 2);
		
	}

	
}
