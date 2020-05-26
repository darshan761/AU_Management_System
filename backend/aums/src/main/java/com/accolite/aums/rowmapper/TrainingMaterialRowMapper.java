/**
 * 
 */
package com.accolite.aums.rowmapper;

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
		trainingMaterial.setTrainingId(rs.getInt(ColumnNames.TRAINING_ID));
		trainingMaterial.setFileName(rs.getString(ColumnNames.FILE_NAME));
		trainingMaterial.setFileType(rs.getString(ColumnNames.FILE_TYPE));
		trainingMaterial.setFile(rs.getBlob(ColumnNames.FILE));
		return trainingMaterial;
		
	};
}
