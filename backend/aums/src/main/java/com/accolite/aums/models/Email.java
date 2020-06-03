/**
 * 
 */
package com.accolite.aums.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author darshan
 *
 */
@Getter @Setter
public class Email {
	
	private String subject;
	private String message;
	private String recepient;
	private int courseId;
	private String instructor;

}
