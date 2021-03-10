/**
 * 
 */
package com.cg.cba.controller;

import java.util.Map;

/**
 * @author Ankitha Suraksha
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cba.exception.UserCredentialsInvalidException;
import com.cg.cba.service.ILoginService;

@RestController
@RequestMapping(value = "/api/v1/login")
public class LoginController {

	@Autowired
	private ILoginService loginService;
	
	@PostMapping( value = "validateUser")
	public ResponseEntity<String> validateUser(@RequestBody Map<String, String> userMap) throws UserCredentialsInvalidException
	{
		String result=loginService.validateUser(userMap.get("username"),userMap.get("password"));
		return new ResponseEntity<String>(result,HttpStatus.OK);
			
	}
	
}