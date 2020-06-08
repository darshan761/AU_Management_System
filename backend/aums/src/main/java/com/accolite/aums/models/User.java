/**
 * 
 */
package com.accolite.aums.models;

import java.sql.Blob;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author darshan
 *
 */
@Getter @Setter @ToString
public class User {
	
	private int userId;
	private String userName;
	private String userEmail;
    private String userDesignation;
    private String userLocation;
    private Blob userImage;

}
