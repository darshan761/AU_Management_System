/**
 * 
 */
package com.accolite.aums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.aums.models.User;
import com.accolite.aums.service.impl.UserServiceImpl;

/**
 * @author darshan
 *
 */
@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping(value= "/")
	public List<User> getAllUser() {
		
		return userService.getAllUsers();
	}
	
	@GetMapping(value= "/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		
		return userService.findUserByEmail(email);
	}

}
