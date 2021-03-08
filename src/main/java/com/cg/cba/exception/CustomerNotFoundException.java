/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.exception;

public class CustomerNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException() {
	}

	public CustomerNotFoundException(String msg)
	{
		super(msg);
	}

}
