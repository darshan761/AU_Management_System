/**
 * 
 */
package com.accolite.aums.dao;

import com.accolite.aums.dto.ResponseDto;

/**
 * @author darshan
 *
 */
public interface InstructorDao {
	public ResponseDto getAllInstructors();

	public ResponseDto findInstructorById(int id);

	public ResponseDto deleteInstructor(int id);

	public ResponseDto findInstructorByCourseId(int id);
}
