/**
 * 
 */
package com.accolite.aums.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author darshan
 *
 */
@Getter @Setter @ToString
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
