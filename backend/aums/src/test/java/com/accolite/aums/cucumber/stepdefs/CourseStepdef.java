/**
 * 
 */
package com.accolite.aums.cucumber.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import com.accolite.aums.controller.CourseController;
import com.accolite.aums.dto.ResponseDto;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author darshan
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = com.accolite.aums.config.AumsApplication.class)
public class CourseStepdef{
	
	private static final String ROOT_URL = "http://localhost:8080/api/course/";
	
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private CourseController courseController;

    private ResponseDto responseDto;
    private ResponseEntity<ResponseDto> response; 
 
    @When("^the client calls course$")
    public void the_client_issues_get_all_courses() throws Throwable {
        
    	response = restTemplate.getForEntity(ROOT_URL, ResponseDto.class);
    	responseDto = courseController.getAllCourses();
    }
    
    @Then("^the client receives response status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
    	HttpStatus currentStatusCode = response.getStatusCode();
    	assertNull(responseDto.getData());
        assertEquals(currentStatusCode.value(), statusCode);
       
    }
    
    @When("^the client calls course (\\d+)$")
    public void the_client_issues_course(int courseId) {
    	response = restTemplate.getForEntity(ROOT_URL+courseId, ResponseDto.class);
    }
    
    @Then("^the client receives response status code of (\\d+) for course$")
    public void the_client_receives_status_code_for_course(int statusCode) throws Throwable {
    	HttpStatus currentStatusCode = response.getStatusCode();
        assertEquals(currentStatusCode.value(), statusCode);
    }

    
}


