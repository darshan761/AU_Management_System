/**
 * 
 */
package com.accolite.aums.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;
import com.accolite.aums.service.impl.CourseServiceImpl;
import com.accolite.aums.utils.Utils;

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
		
		courseList.add(course);
		courseList.add(course2);	
	}

	@Test
	public void getCourseById() throws Exception {

		response.setData(course);
		
		when(courseService.findCourseById(1)).thenReturn(response);
		
		mockMvc.perform(get("/api/course/1")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.courseName", is(course.getCourseName())));
		

	}
	
	@Test
	public void getAllCourse() throws Exception {

		response.setData(courseList);
		
		when(courseService.getAllCourses()).thenReturn(response);
		
		mockMvc.perform(get("/api/course/")).andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.data.length()",is(2)));
	}
	
	@Test
	public void addCourse() throws Exception {

		response.setData(course);
		
		when(courseService.addCourse(course)).thenReturn(response);
		
		mockMvc.perform(post("/api/course/add")
			    .content(Utils.asJsonString(course))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
	}
	
	@Test
	public void updateCourse() throws Exception {

		response.setData(course);
		
		when(courseService.updateCourse(course)).thenReturn(response);
		
		mockMvc.perform(post("/api/course/save")
				 	.content(Utils.asJsonString(course))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andReturn();
	}
	
	@Test
	public void deleteCourse() throws Exception {
		
		response.setData(course);
		
		when(courseService.deleteCourse(1)).thenReturn(response);
		mockMvc.perform(delete("/api/course/delete/2")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void getCourseByCreatorId() throws Exception {

		response.setData(course);
		
		when(courseService.findCoursesByUserId(1)).thenReturn(response);
		mockMvc.perform(get("/api/course/creator/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.courseName", is(course.getCourseName())));
	}
	
	@Test
	public void getCourseByInstructorId() throws Exception {
	
		response.setData(course);
		
		when(courseService.findCoursesByInstructorId(1)).thenReturn(response);
		mockMvc.perform(get("/api/course/instructor/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.courseName", is(course.getCourseName())));
	}
	
	@Test
	public void getCourseCount() throws Exception {
		
		int count = 2;
		response.setData(count);
		
		when(courseService.getCourseCount()).thenReturn(response);
		mockMvc.perform(get("/api/course/count/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data", is(2)));
	}

}
