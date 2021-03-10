/**
 * 
 */
package com.cg.cba.exception;

/**
 * @author arc
 *
 */

//An exception that should be thrown when an admin is not found
public class AdminNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AdminNotFoundException(String message) {
		super(message);
	}

	public AdminNotFoundException() {
		
	}
}
