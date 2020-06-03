/**
 * 
 */
package com.accolite.aums.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.accolite.aums.dao.impl.CourseDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class CourseDaoTest {
	
	@Mock
    private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private CourseDaoImpl courseDao = new CourseDaoImpl();
	


	@Test
	public void getAllCourse() {
//		 CourseDao employeeDAO = new CourseDaoImpl();
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
			ResponseDto response = new ResponseDto();
			
			
			ReflectionTestUtils.setField(courseDao, "jdbcTemplate", jdbcTemplate);
	        when(jdbcTemplate.queryForObject("SELECT * FROM COURSE", List.class))
	          .thenReturn(course);
	 
	        response = courseDao.getAllCourses();
	         List<Course> courses = (List<Course>) response.getData();
	        assertEquals(2, courses.size());
	}
	
//	@Test
//	public 
}
