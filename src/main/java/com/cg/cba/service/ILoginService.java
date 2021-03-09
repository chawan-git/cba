/**
 * 
 */
package com.cg.cba.service;

import com.cg.cba.exception.UserCredentialsInvalidException;

/**
 * @author Ankitha Suraksha
 *
 */
public interface ILoginService {
	public String validateUser(String username, String password) throws UserCredentialsInvalidException;
}
