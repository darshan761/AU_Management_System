/**
 * 
 */
package com.accolite.aums.models;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author darshan
 *
 */
public class UserTest {

	
	@Test
	   public void testCalculateAppriasal() {
		User user = new User();
		user.setUserName("darshan");
			
	      assertEquals("darshan", user.getUserName());
	   }
}
