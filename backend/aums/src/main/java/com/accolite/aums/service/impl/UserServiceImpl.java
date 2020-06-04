/**
 * 
 */
package com.accolite.aums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.aums.dao.impl.UserDaoImpl;
import com.accolite.aums.dto.ResponseDto;
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
	public ResponseDto getAllUsers() {
		return userDao.getAllUsers();
	}
	
	@Override
	public ResponseDto findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

}