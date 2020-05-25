/**
 * 
 */
package com.accolite.aums.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.aums.models.User;

/**
 * @author darshan
 *
 */
@RestController
@RequestMapping("api/login")
public class LoginController {

	@GetMapping(value= "/user")
	public User getUser() {
		User user = new User();
		user.setUserId(69);
		user.setName("Darshan");
		return user;
	}
	
	@GetMapping("/")
	public String home() {
		return "Home reached Successfully!";
	}
	
	@PostMapping("/savesResponse")
	public void saveResponse() {
		
	}
	
}
