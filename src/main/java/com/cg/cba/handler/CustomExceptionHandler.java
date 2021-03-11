package com.cg.cba.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.cba.exception.TripBookingNotFoundException;
import com.cg.cba.exception.UserCredentialsInvalidException;
import com.cg.cba.controller.AdminController;
import com.cg.cba.exception.AdminAlreadyExsistsException;
import com.cg.cba.exception.AdminNotFoundException;
import com.cg.cba.exception.CabAlreadyExistsException;
import com.cg.cba.exception.CabNotFoundException;
import com.cg.cba.exception.CustomerAlreadyExistsException;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.exception.DriverAlreadyExistsException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.exception.InvalidInputException;
import com.cg.cba.exception.TripAlreadyExistsException;
@RestControllerAdvice
public class CustomExceptionHandler {

	private static final Logger log = LogManager.getLogger(AdminController.class);
	
	
	@ExceptionHandler(TripBookingNotFoundException.class)
	public ResponseEntity<String> TripBookingNotFoundException(TripBookingNotFoundException exception)
	{
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> CustomerNotFoundException(CustomerNotFoundException exception)
	{
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
		
	@ExceptionHandler(AdminAlreadyExsistsException.class)
	public ResponseEntity<String> AdminAlreadyExsistsException(AdminAlreadyExsistsException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<String> AdminNotFoundException(AdminNotFoundException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CabAlreadyExistsException.class)
	public ResponseEntity<String> CabAlreadyExistsException(CabAlreadyExistsException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CabNotFoundException.class)
	public ResponseEntity<String> CabNotFoundException(CabNotFoundException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<String> CustomerAlreadyExistsException(CustomerAlreadyExistsException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DriverAlreadyExistsException.class)
	public ResponseEntity<String> DriverAlreadyExistsException(DriverAlreadyExistsException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DriverNotFoundException.class)
	public ResponseEntity<String> DriverNotFoundException(DriverNotFoundException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TripAlreadyExistsException.class)
	public ResponseEntity<String> TripAlreadyExistsException(TripAlreadyExistsException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserCredentialsInvalidException.class)
	public ResponseEntity<String> UserCredentialsInvalidException(UserCredentialsInvalidException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> handleConstraintViolationExceptions(ConstraintViolationException ex) {
		String error;

		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
		List<String> errors = new ArrayList<>(constraintViolations.size());

		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			error = constraintViolation.getMessage();
			errors.add(error);
		}
		return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<String> InvalidInputException(InvalidInputException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> MethodArgumentTypeMismatchException(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>("Enter proper method parameter data type",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> HttpMessageNotReadableException(HttpMessageNotReadableException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>("Enter proper JSON format",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> HttpRequestMethodNotSupportedException(org.springframework.web.HttpRequestMethodNotSupportedException exception){
		log.error(exception.getLocalizedMessage());
		return new ResponseEntity<String>("Mapping Mismatch",HttpStatus.NOT_FOUND);
	}

	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> Exception(Exception exception){
		log.error(exception.fillInStackTrace());
		return new ResponseEntity<String>("Something is not right! We are working on it!",HttpStatus.NOT_FOUND);
	}
}
