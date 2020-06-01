/**
 * 
 */
package com.accolite.aums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.aums.dao.impl.InstructorDaoImpl;
import com.accolite.aums.dto.ResponseDto;
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
	public ResponseDto getAllInstructors() {
		return instructorDaoImpl.getAllInstructors();
	}

	@Override
	public ResponseDto findInstructorById(int id) {
		return instructorDaoImpl.findInstructorById(id);
	}

	@Override
	public ResponseDto deleteInstructor(int id) {
		return instructorDaoImpl.deleteInstructor(id);		
	}

	@Override
	public ResponseDto findInstructorByCourseId(int id) {
		return instructorDaoImpl.findInstructorByCourseId(id);
	}

}
