/**
 * 
 */
package com.accolite.aums.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author darshan
 *
 */
public class Utils {
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
