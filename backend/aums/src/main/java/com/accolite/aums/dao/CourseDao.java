/**
 * 
 */
package com.accolite.aums.dao;

import java.util.List;

import com.accolite.aums.models.Course;
import com.accolite.aums.models.User;

/**
 * @author darshan
 *
 */
public interface CourseDao {
	public List<Course> getAllCourses();
	 
	 public Course findCourseById(int id);
	 
	 public List<Course> findCoursesByUserId(int id);
	 
	 public void addCourse(Course course);
	 
	 public void updateCourse(Course course);
	 
	 public void deleteCourse(int id);
}
