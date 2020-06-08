/**
 * 
 */
package com.accolite.aums.dao;


import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;

/**
 * @author darshan
 *
 */
public interface CourseDao {
	public ResponseDto getAllCourses();
	 
	 public ResponseDto findCourseById(int id);
	 
	 public ResponseDto findCoursesByUserId(int id);
	 
	 public ResponseDto addCourse(Course course);
	 
	 public ResponseDto updateCourse(Course course);
	 
	 public ResponseDto deleteCourse(int id);
}
