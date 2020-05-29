/**
 * 
 */
package com.accolite.aums.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.aums.dao.InstructorDao;
import com.accolite.aums.models.Instructor;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.InstructorRowMapper;

/**
 * @author darshan
 *
 */
@Repository
public class InstructorDaoImpl implements InstructorDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Instructor> getAllInstructors() {
		return jdbcTemplate.query(Queries.GET_ALL_INSTRUCTORS, InstructorRowMapper.InstructorRowMapperLambda);
	}

	@Override
	public Instructor findInstructorById(int id) {
		return jdbcTemplate.queryForObject(Queries.GET_INSTRUCTOR_BY_ID, InstructorRowMapper.InstructorRowMapperLambda, id);
	}

//	@Override
//	public void addInstructor(Instructor instructor) {
//		jdbcTemplate.update(Queries.CREATE_INSTRUCTOR, instructor.getUserId(), instructor.isActive());		
//	}

//	@Override
//	public void updateInstructor(Instructor instructor) {
//		jdbcTemplate.update(Queries.UPDATE_INSTRUCTOR,instructor.getInstructorId());
//	}

	@Override
	public void deleteInstructor(int id) {
		jdbcTemplate.update(Queries.DELETE_INSTRUCTOR, id);
		
	}

	@Override
	public List<Instructor> findInstructorByCourseId(int id) {
		return jdbcTemplate.query(Queries.GET_INSTRUCTORS_BY_COURSE, InstructorRowMapper.InstructorRowMapperLambda, id);
	}

}
