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
    
    @Test
    void findCourseById() {
    	Course course = new Course();
    	course.setCourseId(1);
    	ResponseDto response = new ResponseDto();
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
    	ResponseDto response = new ResponseDto();
		Course course1 = new Course();
		course1.setCourseId(1);
		course1.setCourseName("React");
		course1.setCourseDesc("Frontend Library");
		
		
		Course course2 = new Course();
		course2.setCourseId(2);
		course2.setCourseName("Angular");
		course2.setCourseDesc("Frontend Library");
		
		List<Course> course = new ArrayList<>();
		course.add(course1);
		course.add(course2);
		response.setData(course);
    	
    	when(courseDao.getAllCourses()).thenReturn(response);
    	
    	ResponseDto responseFound = courseService.getAllCourses();
    	List<Course> courseFound = (List<Course>) responseFound.getData();
    	assertThat(courseFound.size())
        .isEqualTo(2);
    }
    
    @Test
    void addCourse() {
    	ResponseDto response = new ResponseDto();
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
		response.setData(course);
		
		when(courseDao.addCourse(course)).thenReturn(response);
		ResponseDto responseFound = courseService.addCourse(course);
		Course courseFound = (Course) responseFound.getData();
		assertThat(responseFound).isNotNull();
		verify(courseDao).addCourse(courseFound);
    }
    
    @Test
	public void updateCourse() throws Exception {
		
		ResponseDto response = new ResponseDto();
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
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
		
		Course course = new Course();
    	course.setCourseId(1);
    	course.setCreatorId(2);
    	ResponseDto response = new ResponseDto();
    	response.setData(course);
    	
    	when(courseDao.findCoursesByUserId(2)).thenReturn(response);
    	int id = 2;
    	
    	ResponseDto responseFound = courseService.findCoursesByUserId(id);
    	Course courseFound = (Course) responseFound.getData();
    	assertThat(courseFound.getCreatorId())
        .isEqualTo(id);
	}
	
	@Test
	public void getCourseByInstructorId() throws Exception {
		Course course = new Course();
    	course.setCourseId(1);
    	ResponseDto response = new ResponseDto();
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
		ResponseDto response = new ResponseDto();
		int count = 2;
		response.setData(count);
		
		when(courseDao.getCourseCount()).thenReturn(response);
		ResponseDto responseFound = courseService.getCourseCount();
		int countFound = (int) responseFound.getData();
		assertThat(countFound)
		       .isEqualTo(2);		
				
		
	}
    
    
}
