/**
 * 
 */
package com.accolite.aums.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author darshan
 *
 */
@Getter @Setter
public class Training {
	
	private int trainingId;
	private int courseId;
	private int instructorId;
	private String feedback;

}
