/**
 * 
 */
package com.accolite.aums.queries;

import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 * @author darshan
 *
 */
public class Queries {

	private Queries() {

	}

	public static final String GET_ALL_COURSES = "SELECT * from course";
	public static final String GET_COURSE_BY_ID = "SELECT * FROM course WHERE course_id = ?";
	public static final String CREATE_COURSE = "INSERT INTO course(course_name, course_desc, course_skills, course_prerequisites, course_location, creator_id) VALUES(?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_COURSE = "UPDATE course SET course_name=?, course_desc=?, course_skills=?, course_prerequisites=?, course_location=? WHERE course_id =?";
	public static final String DELETE_COURSE = "DELETE FROM course WHERE course_id=?";
	
	public static final String GET_COURSES_OF_CREATOR = "SELECT * from course where course.creator_id = ?";
	public static final String GET_COURSES_OF_TRAINER = "SELECT * from course JOIN training ON course.course_id = training.course_id and training.instructor_id = ?";
	public static final String GET_COURSES_OF_BOTH = GET_COURSES_OF_CREATOR + "UNION [DISTINCT]" + GET_COURSES_OF_TRAINER;
	
	
	public static final String GET_ALL_INSTRUCTORS = "SELECT * FROM user JOIN instructor ON user.user_id = instructor.instructor_id";
	public static final String GET_INSTRUCTOR_BY_ID = "SELECT * FROM user JOIN instructor ON user.user_id = instructor.instructor_id where user.user_id=?";
	public static final String GET_INSTRUCTORS_BY_COURSE = "SELECT * FROM user JOIN training ON user.user_id = training.instructor_id and training.course_id =?";
	public static final String CREATE_INSTRUCTOR = "INSERT INTO instructor(instructor_id, active) VALUES(?, ?)";
	public static final String UPDATE_INSTRUCTOR = "UPDATE instructor SET active=? where instructor_id = ?";
	public static final String DELETE_INSTRUCTOR = "DELETE FROM instructor WHERE instructor_id=?";
	
	public static final String GET_ALL_TRAINING = "SELECT * from training";
	public static final String GET_TRAINING_BY_ID = "SELECT * FROM training WHERE training_id = ?";
	public static final String CREATE_TRAINING = "INSERT INTO training(course_id, instructor_id, training_feedback) VALUES( ?, ?, ?)";
	public static final String UPDATE_TRAINING = "UPDATE training SET creator_id=?, instructor_id=?, training_feedback=? where training_id=?";
	public static final String DELETE_TRAINING = "DELETE FROM training WHERE training_id=?";
	
	public static final String GET_ALL_TRAINING_MATERIAL = "SELECT * from training_material";
	public static final String GET_TRAINING_MATERIAL_BY_IDS = "SELECT * FROM training_material join training ON training.training_id = training_material.training_id WHERE training.course_id = ? and training.instructor_id=?";
	public static final String GET_TRAINING_ID_FROM_MATERIAL = "SELECT training_id FROM training JOIN course ON training.course_id = course.course_id where training.course_id =? and training.instructor_id =?";
	public static final String CREATE_TRAINING_MATERIAL = "INSERT INTO training_material(training_id, training_file, training_file_name, training_file_type, training_file_size) VALUES(?,?,?,?,?)";
	public static final String UPDATE_TRAINING_MATERIAL = "UPDATE training_material SET file_name=?, file_type=?, file=? where training_id=?";
	public static final String DELETE_TRAINING_MATERIAL = "DELETE FROM training_material WHERE training_id=?";
}
