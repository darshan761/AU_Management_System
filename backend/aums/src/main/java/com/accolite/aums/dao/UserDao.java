/**
 * 
 */
package com.accolite.aums.dao;

import java.util.List;

import com.accolite.aums.models.User;

/**
 * @author darshan
 *
 */
public interface UserDao {
	 public List<User> getAllUsers();
	 
	 public User findUserById(int id);
	 
	 public User findUserByEmail(String email);
	 
	 public void deleteUser(int id);

}
