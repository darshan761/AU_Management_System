/**
 * 
 */
package com.accolite.aums.service;

import javax.mail.MessagingException;

import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Email;

/**
 * @author darshan
 *
 */

public interface EmailService{
	
	public ResponseDto sendEmail(Email email)  throws MessagingException;
}
