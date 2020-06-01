/**
 * 
 */
package com.accolite.aums.service;

import java.util.List;

import com.accolite.aums.models.User;

/**
 * @author darshan
 *
 */
public interface UserService {

	public List<User> getAllUsers();

	public User findUserById(int id);

	public User findUserByEmail(String email);


}
