/**
 * 
 */
package com.accolite.aums.models;

import java.sql.Blob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author darshan
 *
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private int userId;
	private String userName;
	private String userEmail;
    private String userDesignation;
    private String userLocation;
    private Blob userImage;

}
