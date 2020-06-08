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
public class Training {
	
	private int trainingId;
	private int courseId;
	private int instructorId;
	private String feedback;

}
