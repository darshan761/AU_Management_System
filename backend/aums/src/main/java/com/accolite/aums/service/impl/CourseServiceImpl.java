/**
 * 
 */
package com.accolite.aums.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.aums.dao.impl.CourseDaoImpl;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.User;
import com.accolite.aums.service.CourseService;

/**
 * @author darshan
 *
 */
@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseDaoImpl courseDao;

	@Override
	public List<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}

	@Override
	public Course findCourseById(int id) {
		return courseDao.findCourseById(id);
	}

	@Override
	public List<Course> findCoursesByUserId(int id) {
		return courseDao.findCoursesByUserId(id);
	}
	
	@Override
	public void addCourse(Course course) {
		courseDao.addCourse(course);
		
	}

	@Override
	public void updateCourse(Course course) {
		courseDao.updateCourse(course);
		
	}

	@Override
	public void deleteCourse(int id) {
		courseDao.deleteCourse(id);
		
	}
	@Override
	public List<Course> findCoursesByInstructorId(int id) {
		return courseDao.findCoursesByInstructorId(id);
	}
	
	public int getCourseCount() {
		return courseDao.getCourseCount();
	}
}
