/**
 * 
 */
package com.accolite.aums.models;

import lombok.Data;

/**
 * @author darshan
 *
 */
@Data
public class Email {
	
	private String subject;
	private String message;
	private String recepient;
	private int courseId;
	private String instructor;

}
