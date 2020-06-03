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
	 
	 public ResponseDto findUserById(int id);
	 
	 public ResponseDto findUserByEmail(String email);
	 
	 public ResponseDto deleteUser(int id);

}
