package com.cg.cba.exception;

//User defined unchecked exception class whose object is thrown intentionally when driver with given Id is not present in the database table.
public class DriverNotFoundException extends RuntimeException{
	/**
	 * Bharat Singh
	 */
	
	public DriverNotFoundException() {
		
	}
	private static final long serialVersionUID = 1L;

	public DriverNotFoundException(String message) {
		super(message);
	}
}
