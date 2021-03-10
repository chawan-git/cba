/**
 * 
 */
package com.cg.cba.controller;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Login Controller
@RestController  
@RequestMapping(value = "/api/v1/login")
@Api(value = "Login Controller", description = "REST API for Login Service")
public class LoginController {
	//Log4j 
	public static final Logger Log = LogManager.getLogger(LoginController.class);

	//Loose Coupling of the loginService
	@Autowired
	private ILoginService loginService;
	
	//Method to validate the User
	@ApiOperation(value = "Validate User")
	@PostMapping( value = "validateUser")
	public ResponseEntity<String> validateUser(@RequestBody Map<String, String> userMap) throws UserCredentialsInvalidException
	{
		
		Log.info("Controller Triggerd");
		String result=loginService.validateUser(userMap.get("username"),userMap.get("password"));
		return new ResponseEntity<String>(result,HttpStatus.OK);
			
	}
	
}