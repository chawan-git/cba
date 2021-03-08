/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.exception;

public class ZeroDistanceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ZeroDistanceException() {
	}

	public ZeroDistanceException(String msg)
	{
		super(msg);
	}

}
