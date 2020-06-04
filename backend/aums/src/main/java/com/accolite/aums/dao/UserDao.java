/**
 * 
 */
package com.accolite.aums.dao;

import com.accolite.aums.dto.ResponseDto;

/**
 * @author darshan
 *
 */
public interface UserDao {
	
	 public ResponseDto getAllUsers();
	 	 
	 public ResponseDto findUserByEmail(String email);
	 
}
