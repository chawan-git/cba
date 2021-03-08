/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.exception;

public class TripBookingNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TripBookingNotFoundException() {
	}

	public TripBookingNotFoundException(String msg)
	{
		super(msg);
	}

}
