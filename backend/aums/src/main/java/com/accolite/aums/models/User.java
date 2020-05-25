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
public class User {
	@Getter @Setter
	private int userId;
	
	@Getter @Setter
	private String userName;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
    public String email ;
	
	@Getter @Setter
    public String provider;
	
	@Getter @Setter
    public String providerId ;
	
	@Getter @Setter
    public String image;
	
	@Getter @Setter
    public String token ;
	
	@Getter @Setter
    public String idToken ;
}
