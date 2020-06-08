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
public class Instructor extends User {

	private int instructorId;
	private int trainingId;
	private int courseId;
}
