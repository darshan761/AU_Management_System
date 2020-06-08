/**
 * 
 */
package com.accolite.aums.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author darshan
 *
 */
@Getter @Setter @ToString
public class Email {
	
	private String subject;
	private String message;
	private String recepient;
	private int courseId;
	private String instructor;

}
