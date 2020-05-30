/**
 * 
 */
package com.accolite.aums.models;

import java.io.File;

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
public class TrainingMaterial  {
	
	private int trainingId;
	private int instructorId;
	private int courseId;
	
	private byte[] file;
	private String fileName;
	private String fileType;
}
