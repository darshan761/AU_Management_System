/**
 * 
 */
package com.accolite.aums.rowmapper;

import java.util.Base64;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.aums.constants.ColumnNames;
import com.accolite.aums.models.TrainingMaterial;

/**
 * @author darshan
 *
 */
public class TrainingMaterialRowMapper {
	
	private TrainingMaterialRowMapper() {}

	public static final RowMapper<TrainingMaterial> TrainingMaterialRowMapperLambda = (rs, rowNum) -> {

		TrainingMaterial trainingMaterial = new TrainingMaterial();
		trainingMaterial.setFileId(rs.getInt(ColumnNames.FILE_ID));
		trainingMaterial.setTrainingId(rs.getInt(ColumnNames.TRAINING_ID));
		trainingMaterial.setFileName(rs.getString(ColumnNames.FILE_NAME));
		trainingMaterial.setFileType(rs.getString(ColumnNames.FILE_TYPE));
		trainingMaterial.setCourseId(rs.getInt(ColumnNames.COURSE_ID));
		trainingMaterial.setInstructorId(rs.getInt(ColumnNames.INSTRUCTOR_ID));
		trainingMaterial.setFile(rs.getBlob(ColumnNames.FILE).getBytes(1, (int)rs.getBlob(ColumnNames.FILE).length()));	
		trainingMaterial.setUploadedAt(rs.getTimestamp(ColumnNames.UPLOADED_AT));
		trainingMaterial.setDeletedAt(rs.getTimestamp(ColumnNames.DELETED_AT));
		return trainingMaterial;
		
	};


}
