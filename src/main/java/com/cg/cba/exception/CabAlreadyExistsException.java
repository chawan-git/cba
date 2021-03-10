package com.cg.cba.exception;

//An exception to be thrown when cab already exists
public class CabAlreadyExistsException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CabAlreadyExistsException(String message) {
		super(message);
	}
	
	public CabAlreadyExistsException() {
		
	}
}
