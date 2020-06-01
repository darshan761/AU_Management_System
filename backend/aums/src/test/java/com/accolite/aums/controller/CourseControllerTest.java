/**
 * 
 */
package com.accolite.aums.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;
import com.accolite.aums.service.impl.CourseServiceImpl;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
@AutoConfigureMockMvc
public class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseServiceImpl courseService;

	@Test
	public void getCourseById() throws Exception {

		ResponseDto response = new ResponseDto();
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
		response.setData(course);
		
		
		when(courseService.findCourseById(1)).thenReturn(response);
		
		mockMvc.perform(get("/api/course/1")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$['data'].courseName", is(course.getCourseName())));
	}
	
	@Test
	public void getAllCourse() throws Exception {

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
		
		when(courseService.getAllCourses()).thenReturn(response);
		
		mockMvc.perform(get("/api/course/")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$['data'].length()",is(2)));
	}
	
	@Test
	public void addCourse() throws Exception {

		ResponseDto response = new ResponseDto();
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
		response.setData(course);
		
		when(courseService.addCourse(course)).thenReturn(response);
		
//		mockMvc.perform(post("/api/course/add")
//				.contentType(MediaType.APPLICATION_JSON)
//				.andEx
	}

}
