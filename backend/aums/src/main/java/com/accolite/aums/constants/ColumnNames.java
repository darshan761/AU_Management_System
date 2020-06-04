/**
 * 
 */
package com.accolite.aums.constants;

/**
 * @author darshan
 *
 */
public class ColumnNames {
	
	private ColumnNames() {}

	// User table columns
	public static final String USER_ID = "user_id";
	public static final String USER_NAME = "user_name";
	public static final String USER_EMAIL = "user_email";
	public static final String USER_IMAGE = "user_img";
	public static final String USER_DESIGNATION = "user_designation";
	public static final String USER_LOCATION = "user_location";
	public static final String INSTRUCTOR_ACTIVE = "active";
	public static final String INSTRUCTOR_ID = "instructor_id";

	
	// Course Table columns
	public static final String COURSE_ID = "course_id";
	public static final String COURSE_NAME = "course_name";
	public static final String COURSE_DESCRIPTION = "course_desc";
	public static final String COURSE_SKILLS = "course_skills";
	public static final String COURSE_PREREQUISITES = "course_prerequisites";
	public static final String COURSE_LOCATION = "course_location";
	public static final String COURSE_MONTH = "course_month";
	public static final String COURSE_YEAR = "course_year";
	public static final String CREATOR_ID = "creator_id";
	public static final String MODIFIED_AT = "modified_at";

	
	// Training Table Columns
	public static final String TRAINING_ID = "training_id";
	public static final String TRAINING_FEEDBACK = "training_feedback";
	
	// Training Material Table Columns
	public static final String FILE_NAME = "training_file_name";
	public static final String FILE_TYPE = "training_file_type";
	public static final String FILE = "training_file";
	public static final String FILE_ID = "training_file_id";
	public static final String UPLOADED_AT = "uploaded_at";
	public static final String DELETED_AT = "deleted_at";

	
}
