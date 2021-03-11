/**
 * 
 */
package com.cg.cba.service;



import com.cg.cba.exception.UserCredentialsInvalidException;

/**
 * @author Ankitha Suraksha
 *
 */

//Service Interface for the Login Service
public interface ILoginService {
	
	public String signIn(String username, String password) throws UserCredentialsInvalidException;
	public String signOut(String username, String password) throws UserCredentialsInvalidException;
}
