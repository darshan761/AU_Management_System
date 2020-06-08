/**
 * 
 */
package com.accolite.aums.exception;

/**
 * @author darshan
 *
 */
public class JSONParseException extends Exception{
	private static final long serialVersionUID = 1L;
	private final String message;
	
	public JSONParseException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
