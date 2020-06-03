/**
 * 
 */
package com.accolite.aums.models;


import lombok.Getter;
import lombok.Setter;

/**
 * @author darshan
 *
 */
@Getter @Setter
public class Course {

	private int courseId;
	private int creatorId;
	private String courseName;
	private String courseDesc;
    private String courseLocation ;
    private String courseSkill;
    private String coursePrerequisites;
    
}
