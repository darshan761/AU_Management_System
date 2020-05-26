/**
 * 
 */
package com.accolite.aums.models;

import java.sql.Blob;

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
public class TrainingMaterial {
	
	private int trainingId;
	private Blob file;
	private String fileName;
	private String fileType;
}
