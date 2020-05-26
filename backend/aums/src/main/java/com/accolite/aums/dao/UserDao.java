/**
 * 
 */
package com.accolite.aums.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.aums.models.User;

/**
 * @author darshan
 *
 */
public interface UserDao {
	 public List<User> getAllUsers();
	 
	 public User findUserById(int id);
	 
	 public void addUser(User user);
	 
	 public void updateUser(User user);
	 
	 public void deleteUser(int id);

}
