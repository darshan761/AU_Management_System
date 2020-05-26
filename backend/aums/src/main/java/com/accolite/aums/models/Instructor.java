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
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends User {

	private int instructorId;
	private boolean active;
}
