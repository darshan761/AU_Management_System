/**
 * 
 */
package com.accolite.aums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.aums.dto.ResponseDto;
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
	public ResponseDto getAllUser() {
		
		return userService.getAllUsers();
	}
	
	@GetMapping(value= "/{email}")
	public ResponseDto getUserByEmail(@PathVariable("email") String email) {
		
		return userService.findUserByEmail(email);
	}

}
