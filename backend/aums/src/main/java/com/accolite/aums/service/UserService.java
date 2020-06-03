/**
 * 
 */
package com.accolite.aums.service;

import com.accolite.aums.dto.ResponseDto;

/**
 * @author darshan
 *
 */
public interface UserService {

	public ResponseDto getAllUsers();

	public ResponseDto findUserById(int id);

	public ResponseDto findUserByEmail(String email);


}
