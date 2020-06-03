/**
 * 
 */
package com.accolite.aums.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
	    .andExpect(jsonPath("$.data.courseName", is(course.getCourseName())));
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
	    .andExpect(jsonPath("$.data.length()",is(2)));
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
		
		ResponseDto response = new ResponseDto();
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
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
		ResponseDto response = new ResponseDto();
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
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
		
		ResponseDto response = new ResponseDto();
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
		response.setData(course);
		
		when(courseService.findCoursesByUserId(1)).thenReturn(response);
		mockMvc.perform(get("/api/course/creator/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.courseName", is(course.getCourseName())));
	}
	
	@Test
	public void getCourseByInstructorId() throws Exception {
		ResponseDto response = new ResponseDto();
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
		response.setData(course);
		
		when(courseService.findCoursesByInstructorId(1)).thenReturn(response);
		mockMvc.perform(get("/api/course/instructor/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.courseName", is(course.getCourseName())));
	}
	
	@Test
	public void getCourseCount() throws Exception {
		ResponseDto response = new ResponseDto();
		int count = 2;
		response.setData(count);
		
		when(courseService.getCourseCount()).thenReturn(response);
		mockMvc.perform(get("/api/course/count/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data", is(2)));
	}

}
