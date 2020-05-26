/**
 * 
 */
package com.accolite.aums.service;

import java.util.List;

import com.accolite.aums.models.Course;
import com.accolite.aums.models.Instructor;

/**
 * @author darshan
 *
 */
public interface InstructorService {
	public List<Instructor> getAllInstructors();

	public Instructor findInstructorById(int id);

	public void addInstructor(Instructor instructor);

	public void updateInstructor(Instructor instructor);

	public void deleteInstructor(int id);
}
