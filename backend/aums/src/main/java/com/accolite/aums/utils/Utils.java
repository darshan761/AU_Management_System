/**
 * 
 */
package com.accolite.aums.utils;

import com.accolite.aums.exception.JSONParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author darshan
 *
 */
public class Utils {
	
	private Utils() {}
	
	public static String asJsonString(final Object obj) throws JSONParseException {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new JSONParseException(e.getMessage());
	    }
	}
}
