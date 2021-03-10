/**
 * 
 */
package com.cg.cba.exception;

/**
 * @author arc
 *
 */

//An exception that should be thrown when an admin already exists
public class AdminAlreadyExsistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AdminAlreadyExsistsException(String message) {
		super(message);
	}
	
	public AdminAlreadyExsistsException() {
		
	}
}
