/**
 * 
 */
package com.accolite.aums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.aums.dao.impl.CourseDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;
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
	public ResponseDto getAllCourses() {
		return courseDao.getAllCourses();
	}

	@Override
	public ResponseDto findCourseById(int id) {
		return courseDao.findCourseById(id);
	}

	@Override
	public ResponseDto findCoursesByUserId(int id) {
		return courseDao.findCoursesByUserId(id);
	}
	
	@Override
	public ResponseDto addCourse(Course course) {
		return courseDao.addCourse(course);
		
	}

	@Override
	public ResponseDto updateCourse(Course course) {
		return courseDao.updateCourse(course);
		
	}

	@Override
	public ResponseDto deleteCourse(int id) {
		return courseDao.deleteCourse(id);
		
	}
	@Override
	public ResponseDto findCoursesByInstructorId(int id) {
		return courseDao.findCoursesByInstructorId(id);
	}
	
	@Override
	public ResponseDto getCourseCount() {
		return courseDao.getCourseCount();
	}

	/**
	 * @param id
	 * @return
	 */
	public ResponseDto getCourseVersion(int id) {
		// TODO Auto-generated method stub
		return courseDao.getCourseVersion(id);
	}
}
