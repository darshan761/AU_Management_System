/**
 * 
 */
package com.accolite.aums.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.aums.constants.ColumnNames;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.Instructor;

/**
 * @author darshan
 *
 */

public class InstructorRowMapper {
	
	private InstructorRowMapper() {}

	public static final RowMapper<Instructor> InstructorRowMapperLambda = (rs, rowNum) -> {

		Instructor instructor = new Instructor();
		instructor.setUserId(rs.getInt(ColumnNames.USER_ID));
		instructor.setUserName(rs.getString(ColumnNames.USER_NAME));
		instructor.setUserEmail(rs.getString(ColumnNames.USER_EMAIL));
		instructor.setActive(rs.getBoolean(ColumnNames.INSTRUCTOR_ACTIVE));
		instructor.setUserImage(rs.getBlob(ColumnNames.USER_IMAGE));
		instructor.setUserLocation(rs.getString(ColumnNames.USER_LOCATION));
		instructor.setInstructorId(rs.getInt(ColumnNames.INSTRUCTOR_ID));
		return instructor;
		
	};

}
