package com.cg.cba.exception;

//User defined unchecked exception class whose object is thrown intentionally when Driver with given Id is already present in the database table.
public class DriverAlreadyExistsException extends RuntimeException{

	/**
	 * Bharat Singh
	 */
	private static final long serialVersionUID = 1L;

	public DriverAlreadyExistsException(String message) {
		super(message);
	}
}
