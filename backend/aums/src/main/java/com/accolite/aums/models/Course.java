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
public class Course {

	private int courseId;
	private int creatorId;
	private String courseName;
	private String courseDesc;
    private String courseLocation ;
    private String courseSkill;
    private String coursePrerequisites;
    private String courseMonth;
    private String courseYear;
    private Timestamp modifiedAt;
    
}
