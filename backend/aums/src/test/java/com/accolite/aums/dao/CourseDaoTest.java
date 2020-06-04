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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.aums.dao.impl.CourseDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
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

	ResponseDto response = new ResponseDto();
	Course course = new Course();
	Course course2 = new Course();
	List<Course> courseList = new ArrayList<>();

	@BeforeEach
	public void init() {
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");

		course2.setCourseId(2);
		course2.setCourseName("Angular");
		course2.setCourseDesc("Frontend Library");
		course2.setCreatorId(2);
		courseList.add(course);
		courseList.add(course2);
	}

	@Test
	public void getAllCourse() throws DataAccessException {

		when(jdbcTemplate.query(Queries.GET_ALL_COURSES, CourseRowMapper.CourseRowMapperLambda)).thenReturn(courseList);

		response = courseDao.getAllCourses();
		List<Course> courses = (List<Course>) response.getData();
		assertEquals(2, courses.size());

		doThrow(new RuntimeException()).when(jdbcTemplate).query(Queries.GET_ALL_COURSES,
				CourseRowMapper.CourseRowMapperLambda);

		response = courseDao.getAllCourses();

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).query(Queries.GET_ALL_COURSES, CourseRowMapper.CourseRowMapperLambda);

	}

	@Test
	public void getCourseById() throws Exception {

		when(jdbcTemplate.queryForObject(Queries.GET_COURSE_BY_ID, CourseRowMapper.CourseRowMapperLambda, 1))
				.thenReturn(course);

		response = courseDao.findCourseById(1);
		Course courseFound = (Course) response.getData();
		assertEquals(1, courseFound.getCourseId());

		doThrow(new RuntimeException()).when(jdbcTemplate).queryForObject(Queries.GET_COURSE_BY_ID,
				CourseRowMapper.CourseRowMapperLambda, 1);

		response = courseDao.findCourseById(1);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).queryForObject(Queries.GET_COURSE_BY_ID, CourseRowMapper.CourseRowMapperLambda,
				1);
	}

	@Test
	public void addCourse() throws Exception {

		when(jdbcTemplate.update(Queries.CREATE_COURSE, course.getCourseName(), course.getCourseDesc(),
				course.getCourseSkill(), course.getCoursePrerequisites(), course.getCourseLocation(),
				course.getCourseMonth(), course.getCourseYear(), course.getCreatorId())).thenReturn(1);

		response = courseDao.addCourse(course);
		assertThat(response).isNotNull();
		assertEquals(1, response.getAdditionalData());

		doThrow(new RuntimeException()).when(jdbcTemplate).update(Queries.CREATE_COURSE, course.getCourseName(),
				course.getCourseDesc(), course.getCourseSkill(), course.getCoursePrerequisites(),
				course.getCourseLocation(), course.getCourseMonth(), course.getCourseYear(), course.getCreatorId());

		response = courseDao.addCourse(course);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).update(Queries.CREATE_COURSE, course.getCourseName(), course.getCourseDesc(),
				course.getCourseSkill(), course.getCoursePrerequisites(), course.getCourseLocation(),
				course.getCourseMonth(), course.getCourseYear(), course.getCreatorId());

	}

	@Test
	public void updateCourse() throws Exception {

		when(jdbcTemplate.update(Queries.UPDATE_COURSE, course.getCourseName(), course.getCourseDesc(),
				course.getCourseSkill(), course.getCoursePrerequisites(), course.getCourseLocation(),
				course.getCourseMonth(), course.getCourseYear(), course.getCourseId())).thenReturn(1);

		response = courseDao.updateCourse(course);
		assertThat(response).isNotNull();
		assertEquals(1, response.getAdditionalData());

		doThrow(new RuntimeException()).when(jdbcTemplate).update(Queries.UPDATE_COURSE, course.getCourseName(),
				course.getCourseDesc(), course.getCourseSkill(), course.getCoursePrerequisites(),
				course.getCourseLocation(), course.getCourseMonth(), course.getCourseYear(), course.getCourseId());

		response = courseDao.updateCourse(course);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).update(Queries.UPDATE_COURSE, course.getCourseName(), course.getCourseDesc(),
				course.getCourseSkill(), course.getCoursePrerequisites(), course.getCourseLocation(),
				course.getCourseMonth(), course.getCourseYear(), course.getCourseId());

	}

	@Test
	public void deleteCourse() throws Exception {

		when(jdbcTemplate.update(Queries.DELETE_COURSE, 1)).thenReturn(1);

		response = courseDao.deleteCourse(1);
		assertThat(response).isNotNull();
		assertEquals(1, response.getAdditionalData());

		doThrow(new RuntimeException()).when(jdbcTemplate).update(Queries.DELETE_COURSE, 1);
		response = courseDao.deleteCourse(1);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).update(Queries.DELETE_COURSE, 1);
	}

//	
	@Test
	public void getCourseByCreatorId() throws Exception {

		when(jdbcTemplate.query(Queries.GET_COURSES_OF_CREATOR, CourseRowMapper.CourseRowMapperLambda, 2))
				.thenReturn(courseList);

		response = courseDao.findCoursesByUserId(2);
		List<Course> courseFound = (List<Course>) response.getData();
		assertEquals(2, courseFound.size());

		doThrow(new RuntimeException()).when(jdbcTemplate).query(Queries.GET_COURSES_OF_CREATOR,
				CourseRowMapper.CourseRowMapperLambda, 2);
		response = courseDao.findCoursesByUserId(2);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).query(Queries.GET_COURSES_OF_CREATOR, CourseRowMapper.CourseRowMapperLambda, 2);
	}

	@Test
	public void getCourseByInstructorId() throws Exception {

		when(jdbcTemplate.query(Queries.GET_COURSES_OF_TRAINER, CourseRowMapper.CourseRowMapperLambda, 3))
				.thenReturn(courseList);
		response = courseDao.findCoursesByInstructorId(3);
		List<Course> courseFound = (List<Course>) response.getData();
		assertEquals(2, courseFound.size());

		doThrow(new RuntimeException()).when(jdbcTemplate).query(Queries.GET_COURSES_OF_TRAINER,
				CourseRowMapper.CourseRowMapperLambda, 3);
		response = courseDao.findCoursesByInstructorId(3);

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).query(Queries.GET_COURSES_OF_TRAINER, CourseRowMapper.CourseRowMapperLambda, 3);
	}

//	
	@Test
	public void getCourseCount() throws Exception {

		when(jdbcTemplate.queryForObject(Queries.GET_COURSE_COUNT, Integer.class)).thenReturn(2);
		response = courseDao.getCourseCount();
		int count = (int) response.getData();
		assertEquals(2, count);

		doThrow(new RuntimeException()).when(jdbcTemplate).queryForObject(Queries.GET_COURSE_COUNT, Integer.class);
		response = courseDao.getCourseCount();

		assertEquals(ResponseType.FAILURE, response.getResponseType());

		verify(jdbcTemplate, times(2)).queryForObject(Queries.GET_COURSE_COUNT, Integer.class);

	}
//	

}
