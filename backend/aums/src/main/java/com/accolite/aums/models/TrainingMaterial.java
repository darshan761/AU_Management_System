/**
 * 
 */
package com.accolite.aums.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author darshan
 *
 */
@Getter @Setter
public class TrainingMaterial  {
	
	private int trainingId;
	private int fileId;
	private int instructorId;
	private int courseId;
	private Timestamp uploadedAt;
	private Timestamp deletedAt;
	private byte[] file;
	private String fileName;
	private String fileType;
}
