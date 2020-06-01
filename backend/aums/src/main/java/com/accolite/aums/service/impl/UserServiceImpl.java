/**
 * 
 */
package com.accolite.aums.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.aums.dao.impl.UserDaoImpl;
import com.accolite.aums.models.User;
import com.accolite.aums.service.UserService;

/**
 * @author darshan
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDaoImpl userDao;

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User findUserById(int id) {
		return userDao.findUserById(id);
	}
	
	@Override
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

}