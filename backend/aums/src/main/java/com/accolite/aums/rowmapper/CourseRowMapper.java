/**
 * 
 */
package com.accolite.aums.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.aums.constants.ColumnNames;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.User;

/**
 * @author darshan
 *
 */
public class CourseRowMapper {
	
	private CourseRowMapper() {}

	public static final RowMapper<Course> CourseRowMapperLambda = (rs, rowNum) -> {

		Course course = new Course();
		course.setCourseId(rs.getInt(ColumnNames.COURSE_ID));
		course.setCourseName(rs.getString(ColumnNames.COURSE_NAME));
		course.setCourseDesc(rs.getString(ColumnNames.COURSE_DESCRIPTION));
		course.setCourseLocation(rs.getString(ColumnNames.COURSE_LOCATION));
		course.setCourseSkill(rs.getString(ColumnNames.COURSE_SKILLS));
		course.setCoursePrerequisites(rs.getString(ColumnNames.COURSE_PREREQUISITES));
		course.setCourseMonth(rs.getString(ColumnNames.COURSE_MONTH));
		course.setCourseYear(rs.getString(ColumnNames.COURSE_YEAR));
		course.setCreatorId(rs.getInt(ColumnNames.CREATOR_ID));
		course.setModifiedAt(rs.getTimestamp(ColumnNames.MODIFIED_AT));
		return course;
		
	};

}
