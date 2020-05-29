/**
 * 
 */
package com.accolite.aums.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.aums.dao.impl.InstructorDaoImpl;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.Instructor;
import com.accolite.aums.service.InstructorService;

/**
 * @author darshan
 *
 */
@Service
public class InstructorServiceImpl implements InstructorService{

	@Autowired
	private InstructorDaoImpl instructorDaoImpl;
	
	@Override
	public List<Instructor> getAllInstructors() {
		return instructorDaoImpl.getAllInstructors();
	}

	@Override
	public Instructor findInstructorById(int id) {
		return instructorDaoImpl.findInstructorById(id);
	}

//	@Override
//	public void addInstructor(Instructor instructor) {
//		instructorDaoImpl.addInstructor(instructor);		
//	}
//
//	@Override
//	public void updateInstructor(Instructor instructor) {
//		instructorDaoImpl.updateInstructor(instructor);		
//	}

	@Override
	public void deleteInstructor(int id) {
		instructorDaoImpl.deleteInstructor(id);		
	}

	@Override
	public List<Instructor> findInstructorByCourseId(int id) {
		return instructorDaoImpl.findInstructorByCourseId(id);
	}

}
