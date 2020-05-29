/**
 * 
 */
package com.accolite.aums.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.aums.constants.ColumnNames;
import com.accolite.aums.models.Training;

/**
 * @author darshan
 *
 */
public class TrainingRowMapper {

	private TrainingRowMapper() {}

	public static final RowMapper<Training> TrainingRowMapperLambda = (rs, rowNum) -> {

		Training training = new Training();
		training.setFeedback(rs.getString(ColumnNames.TRAINING_FEEDBACK));
		training.setCourseId(rs.getInt(ColumnNames.COURSE_ID));
		training.setInstructorId(rs.getInt(ColumnNames.INSTRUCTOR_ID));
		return training;
		
	};

}
