/**
 * 
 */
package com.accolite.aums.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.accolite.aums.dao.impl.CourseDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;
import com.accolite.aums.service.impl.CourseServiceImpl;
import com.accolite.aums.utils.Utils;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class CourseServiceTest {

	@Mock
    private CourseDaoImpl courseDao;

    @InjectMocks
    private CourseServiceImpl courseService;
    
    ResponseDto response = new ResponseDto();
	Course course = new Course();
	Course course2 = new Course();
	List<Course> courseList = new ArrayList<>();
    
    @BeforeEach
	public void init() {
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
		
		course2.setCourseId(2);
		course2.setCourseName("Angular");
		course2.setCourseDesc("Frontend Library");
		course2.setCreatorId(2);
		courseList.add(course);
		courseList.add(course2);	
	}
    
    @Test
    void findCourseById() {
    	response.setData(course);
    	
    	when(courseDao.findCourseById(1)).thenReturn(response);
    	int id = 1;
    	
    	ResponseDto responseFound = courseService.findCourseById(id);
    	Course courseFound = (Course) responseFound.getData();
    	assertThat(courseFound.getCourseId())
        .isEqualTo(id);
    }
    
    @Test
    void findAllCourse() {
		response.setData(courseList);
    	
    	when(courseDao.getAllCourses()).thenReturn(response);
    	
    	ResponseDto responseFound = courseService.getAllCourses();
    	List<Course> courseFound = (List<Course>) responseFound.getData();
    	assertThat(courseFound.size())
        .isEqualTo(2);
    }
    
    @Test
    void addCourse() {

		response.setData(course);
		
		when(courseDao.addCourse(course)).thenReturn(response);
		ResponseDto responseFound = courseService.addCourse(course);
		Course courseFound = (Course) responseFound.getData();
		assertThat(responseFound).isNotNull();
		verify(courseDao).addCourse(courseFound);
    }
    
    @Test
	public void updateCourse() throws Exception {
		
		response.setData(course);
		
		when(courseDao.updateCourse(course)).thenReturn(response);
		ResponseDto responseFound = courseService.updateCourse(course);
		Course courseFound = (Course) responseFound.getData();
		assertThat(responseFound).isNotNull();
		verify(courseDao).updateCourse(courseFound);
		
	}
	
	@Test
	public void deleteCourse() throws Exception {
		final int courseId = 1;
		courseService.deleteCourse(courseId);
		courseService.deleteCourse(courseId);
		
		verify(courseDao, times(2)).deleteCourse(courseId);
	}
	
	@Test
	public void getCourseByCreatorId() throws Exception {
		
    	response.setData(course2);
    	
    	when(courseDao.findCoursesByUserId(2)).thenReturn(response);
    	int id = 2;
    	
    	ResponseDto responseFound = courseService.findCoursesByUserId(id);
    	Course courseFound = (Course) responseFound.getData();
    	assertThat(courseFound.getCreatorId())
        .isEqualTo(id);
	}
	
	@Test
	public void getCourseByInstructorId() throws Exception {

    	response.setData(course);
    	
    	when(courseDao.findCoursesByInstructorId(1)).thenReturn(response);
    	int id = 1;
    	
    	ResponseDto responseFound = courseService.findCoursesByInstructorId(id);
    	Course courseFound = (Course) responseFound.getData();
    	assertThat(courseFound.getCourseId())
        .isEqualTo(1);
	}
	
	@Test
	public void getCourseCount() throws Exception {
		int count = 2;
		response.setData(count);
		
		when(courseDao.getCourseCount()).thenReturn(response);
		ResponseDto responseFound = courseService.getCourseCount();
		int countFound = (int) responseFound.getData();
		assertThat(countFound)
		       .isEqualTo(2);		
				
		
	}
    
    
}
