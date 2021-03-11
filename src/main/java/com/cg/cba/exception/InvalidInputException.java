/**
 * 
 */
package com.cg.cba.exception;

/**
 * @author arc
 *
 */
public class InvalidInputException  extends RuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		super();
	}
	
	public InvalidInputException(String message) {
		super(message);
	}
}
