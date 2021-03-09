package com.cg.cba.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.cba.exception.TripBookingNotFoundException;
import com.cg.cba.exception.UserCredentialsInvalidException;
import com.cg.cba.exception.AdminAlreadyExsistsException;
import com.cg.cba.exception.AdminNotFoundException;
import com.cg.cba.exception.CabAlreadyExistsException;
import com.cg.cba.exception.CabNotFoundException;
import com.cg.cba.exception.CustomerAlreadyExistsException;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.exception.DriverAlreadyExistsException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.exception.TripAlreadyExistsException;
import com.cg.cba.exception.ZeroDistanceException;
@RestControllerAdvice
public class CustomExceptionHandler {

	
	@ExceptionHandler(TripBookingNotFoundException.class)
	public ResponseEntity<String> TripBookingNotFoundException(TripBookingNotFoundException tbnfe)
	{
		return new ResponseEntity<String>(tbnfe.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> CustomerNotFoundException(CustomerNotFoundException cnfe)
	{
		return new ResponseEntity<String>(cnfe.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ZeroDistanceException.class)
	public ResponseEntity<String> ZeroDistanceException(ZeroDistanceException tbnfe)
	{
		return new ResponseEntity<String>(tbnfe.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> DataIntegrityViolationException(DataIntegrityViolationException dive)
	{
		return new ResponseEntity<String>("Some of the Fields Cannot be Empty",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AdminAlreadyExsistsException.class)
	public ResponseEntity<String> AdminAlreadyExsistsException(AdminAlreadyExsistsException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<String> AdminNotFoundException(AdminNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CabAlreadyExistsException.class)
	public ResponseEntity<String> CabAlreadyExistsException(CabAlreadyExistsException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CabNotFoundException.class)
	public ResponseEntity<String> CabNotFoundException(CabNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<String> CustomerAlreadyExistsException(CustomerAlreadyExistsException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DriverAlreadyExistsException.class)
	public ResponseEntity<String> DriverAlreadyExistsException(DriverAlreadyExistsException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DriverNotFoundException.class)
	public ResponseEntity<String> DriverNotFoundException(DriverNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TripAlreadyExistsException.class)
	public ResponseEntity<String> TripAlreadyExistsException(TripAlreadyExistsException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserCredentialsInvalidException.class)
	public ResponseEntity<String> UserCredentialsInvalidException(UserCredentialsInvalidException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> Exception(Exception exception){
		return new ResponseEntity<String>("Something is not right! We are working on it!",HttpStatus.NOT_FOUND);
	}
}
