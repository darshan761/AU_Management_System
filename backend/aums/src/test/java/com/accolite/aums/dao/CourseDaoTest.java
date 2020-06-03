/**
 * 
 */
package com.accolite.aums.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.aums.dao.impl.CourseDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.CourseRowMapper;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class CourseDaoTest {
	
	@Mock
    private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private CourseDaoImpl courseDao;
	

	@Test
	public void getAllCourse() throws Exception {
			Course course1 = new Course();
			course1.setCourseId(1);
			course1.setCourseName("React");
			course1.setCourseDesc("Frontend Library");
			
			Course course2 = new Course();
			course2.setCourseId(2);
			course2.setCourseName("Angular");
			course2.setCourseDesc("Frontend Library");
			
			List<Course> course = new ArrayList<>();
			course.add(course1);
			course.add(course2);			
			
			when(jdbcTemplate.query(Queries.GET_ALL_COURSES, CourseRowMapper.CourseRowMapperLambda ))
	          .thenReturn(course);
	 
			ResponseDto response = courseDao.getAllCourses();
	         List<Course> courses = (List<Course>) response.getData();
	        assertEquals(2, courses.size());
	}
	
	@Test
	public void getCourseById() throws Exception {

		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
		
		when(jdbcTemplate.queryForObject(Queries.GET_COURSE_BY_ID, CourseRowMapper.CourseRowMapperLambda, 1 )).thenReturn(course);
		
		ResponseDto response = courseDao.findCourseById(1);
		Course courseFound = (Course) response.getData();
		assertEquals(1, courseFound.getCourseId());
	}
	
	@Test
	public void addCourse() throws Exception {

		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseSkill("skills");
		course.setCourseLocation("Mumbai");
		course.setCoursePrerequisites("pre");
		course.setCourseDesc("Frontend Library");
		
		when(jdbcTemplate.update(Queries.CREATE_COURSE, course.getCourseName(), course.getCourseDesc(),
				course.getCourseSkill(), course.getCoursePrerequisites(), course.getCourseLocation(),
				course.getCreatorId())).thenReturn(1);
		
		ResponseDto responseFound = courseDao.addCourse(course);
		assertThat(responseFound).isNotNull();
		assertEquals(1, responseFound.getAdditionalData());
		
	}
	
	@Test
	public void updateCourse() throws Exception {
		
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");

		
		when(jdbcTemplate.update(Queries.UPDATE_COURSE, course.getCourseName(), course.getCourseDesc(),
				course.getCourseSkill(), course.getCoursePrerequisites(), course.getCourseLocation(), course.getCourseId())).thenReturn(1);
		
		ResponseDto responseFound = courseDao.updateCourse(course);
		assertThat(responseFound).isNotNull();
		assertEquals(1, responseFound.getAdditionalData());
		
	}
	
	@Test
	public void deleteCourse() throws Exception {
		
		when(jdbcTemplate.update(Queries.DELETE_COURSE, 1)).thenReturn(1);
		
		ResponseDto responseFound = courseDao.deleteCourse(1);
		assertThat(responseFound).isNotNull();
		assertEquals(1, responseFound.getAdditionalData());
	}
//	
	@Test
	public void getCourseByCreatorId() throws Exception {
		
		Course course = new Course();
		course.setCourseId(1);
		course.setCreatorId(2);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
		
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		
		when(jdbcTemplate.query(Queries.GET_COURSES_OF_CREATOR, CourseRowMapper.CourseRowMapperLambda, 2)).thenReturn(courses);

		ResponseDto response = courseDao.findCoursesByUserId(2);
		List<Course> courseFound = (List<Course>) response.getData();
		assertEquals(1, courseFound.size());
	}
	
	@Test
	public void getCourseByInstructorId() throws Exception {
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		
		
		when(jdbcTemplate.query(Queries.GET_COURSES_OF_TRAINER, CourseRowMapper.CourseRowMapperLambda, 3)).thenReturn(courses);
		ResponseDto response = courseDao.findCoursesByInstructorId(3);
		List<Course> courseFound = (List<Course>) response.getData();
		assertEquals(1, courseFound.size());
	}
//	
	@Test
	public void getCourseCount() throws Exception {
		
		when(jdbcTemplate.queryForObject(Queries.GET_COURSE_COUNT, Integer.class)).thenReturn(2);
		ResponseDto response = courseDao.getCourseCount();
		int count = (int)response.getData();
		assertEquals(2, count);
	}
//	

}
