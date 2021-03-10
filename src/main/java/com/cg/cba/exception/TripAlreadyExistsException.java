package com.cg.cba.exception;

public class TripAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TripAlreadyExistsException(String message){
		super(message);
	}
	
	public TripAlreadyExistsException() {
	}
	
}
