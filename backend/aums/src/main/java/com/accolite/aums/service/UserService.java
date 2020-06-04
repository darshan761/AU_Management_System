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

	public ResponseDto findUserByEmail(String email);


}
