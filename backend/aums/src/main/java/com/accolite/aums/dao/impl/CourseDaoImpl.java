/**
 * 
 */
package com.accolite.aums.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.aums.dao.CourseDao;
import com.accolite.aums.models.Course;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.CourseRowMapper;

/**
 * @author darshan
 *
 */
@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Course> getAllCourses() {
		return jdbcTemplate.query(Queries.GET_ALL_COURSES, CourseRowMapper.CourseRowMapperLambda);
	}

	@Override
	public Course findCourseById(int id) {
		return jdbcTemplate.queryForObject(Queries.GET_COURSE_BY_ID, CourseRowMapper.CourseRowMapperLambda, id);
	}

	@Override
	public void addCourse(Course course) {
		jdbcTemplate.update(Queries.CREATE_COURSE, course.getCourseName(), course.getCourseDesc(),
				course.getCourseSkill(), course.getCoursePrerequisites(), course.getCourseLocation(),
				course.getCreatorId());
	}

	@Override
	public void updateCourse(Course course) {
		jdbcTemplate.update(Queries.UPDATE_COURSE, course.getCourseName(), course.getCourseDesc(),
				course.getCourseSkill(), course.getCoursePrerequisites(), course.getCourseLocation(), course.getCourseId());
	}

	@Override
	public void deleteCourse(int id) {
		jdbcTemplate.update(Queries.DELETE_COURSE, id);

	}

	@Override
	public List<Course> findCoursesByUserId(int id) {
		return jdbcTemplate.query(Queries.GET_COURSES_OF_CREATOR, CourseRowMapper.CourseRowMapperLambda, id);	
	}


	public List<Course> findCoursesByInstructorId(int id) {
		return jdbcTemplate.query(Queries.GET_COURSES_OF_TRAINER, CourseRowMapper.CourseRowMapperLambda, id);
	}
	
	public int getCourseCount() {
		return jdbcTemplate.queryForObject(Queries.GET_COURSE_COUNT, Integer.class);
	}

}
