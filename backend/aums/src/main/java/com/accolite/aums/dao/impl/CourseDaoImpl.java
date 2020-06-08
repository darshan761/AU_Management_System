/**
 * 
 */
package com.accolite.aums.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.aums.dao.CourseDao;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.models.Course;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.CourseRowMapper;

/**
 * @author darshan
 *
 */
@Repository
public class CourseDaoImpl implements CourseDao {

	public static final Logger LOGGER = LoggerFactory.getLogger(CourseDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseDto getAllCourses() {
		ResponseDto response = new ResponseDto();
		try {
			response.setData(jdbcTemplate.query(Queries.GET_ALL_COURSES, CourseRowMapper.CourseRowMapperLambda));
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getData());
			
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);

		}
		return response;
	}

	@Override
	public ResponseDto findCourseById(int id) {

		ResponseDto response = new ResponseDto();
		try {
			response.setData(
					jdbcTemplate.queryForObject(Queries.GET_COURSE_BY_ID, CourseRowMapper.CourseRowMapperLambda, id));
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getData());

		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);
			
		}
		return response;

	}

	@Override
	public ResponseDto addCourse(Course course) {

		ResponseDto response = new ResponseDto();
		try {
			response.setAdditionalData(
					jdbcTemplate.update(Queries.CREATE_COURSE, course.getCourseName(), course.getCourseDesc(),
							course.getCourseSkill(), course.getCoursePrerequisites(), course.getCourseLocation(),
							course.getCourseMonth(), course.getCourseYear(), course.getCreatorId()));
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getAdditionalData());
			
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);
			
		}
		return response;

	}

	@Override
	public ResponseDto updateCourse(Course course) {

		ResponseDto response = new ResponseDto();
		try {
			response.setAdditionalData(jdbcTemplate.update(Queries.UPDATE_COURSE, course.getCourseName(),
					course.getCourseDesc(), course.getCourseSkill(), course.getCoursePrerequisites(),
					course.getCourseLocation(), course.getCourseMonth(), course.getCourseYear(), course.getCourseId()));
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getAdditionalData());
			
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);
			

		}
		return response;

	}

	@Override
	public ResponseDto deleteCourse(int id) {

		ResponseDto response = new ResponseDto();
		try {
			response.setAdditionalData(jdbcTemplate.update(Queries.DELETE_COURSE, id));
			response.setResponseType(ResponseType.SUCCESS);
			LOGGER.info("{}", response.getAdditionalData());
			
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);
			
		}
		return response;

	}

	@Override
	public ResponseDto findCoursesByUserId(int id) {
		ResponseDto response = new ResponseDto();
		try {
			response.setData(
					jdbcTemplate.query(Queries.GET_COURSES_OF_CREATOR, CourseRowMapper.CourseRowMapperLambda, id));
			response.setResponseType(ResponseType.SUCCESS);
			
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);

		}
		return response;

	}

	public ResponseDto findCoursesByInstructorId(int id) {
		ResponseDto response = new ResponseDto();
		try {
			response.setData(
					jdbcTemplate.query(Queries.GET_COURSES_OF_TRAINER, CourseRowMapper.CourseRowMapperLambda, id));
			response.setResponseType(ResponseType.SUCCESS);
			
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);

		}
		return response;

	}

	public ResponseDto getCourseCount() {
		ResponseDto response = new ResponseDto();
		try {
			response.setData(jdbcTemplate.queryForObject(Queries.GET_COURSE_COUNT, Integer.class));
			response.setResponseType(ResponseType.SUCCESS);
			
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
			LOGGER.error("{}", e);

		}
		return response;
	}

}
