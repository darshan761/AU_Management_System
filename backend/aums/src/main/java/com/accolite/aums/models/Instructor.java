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
public class Instructor extends User {

	private int instructorId;
	private int trainingId;
	private int courseId;
}
