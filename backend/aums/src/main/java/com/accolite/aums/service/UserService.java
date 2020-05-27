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

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(int id);

}
