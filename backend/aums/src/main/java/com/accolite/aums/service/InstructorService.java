/**
 * 
 */
package com.accolite.aums.service;

import com.accolite.aums.dto.ResponseDto;

/**
 * @author darshan
 *
 */
public interface InstructorService {
	public ResponseDto getAllInstructors();

	public ResponseDto findInstructorById(int id);

	public ResponseDto deleteInstructor(int id);

	public ResponseDto findInstructorByCourseId(int id);
}
