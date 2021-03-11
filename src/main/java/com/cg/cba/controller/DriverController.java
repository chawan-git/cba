/**
 * Author: Bharat Singh
 */

package com.cg.cba.controller;



import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cba.entities.Driver;
import com.cg.cba.exception.CabNotFoundException;
import com.cg.cba.exception.DriverAlreadyExistsException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.exception.InvalidInputException;
import com.cg.cba.service.IDriverService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Controller class for RESTful web services.
@RestController
@RequestMapping(path = "api/v1/driver")
@Api(description = "REST API of Driver Table")

public class DriverController {
	
	
	@Autowired
	IDriverService iDriverService;
	
	//Creating a Logger Object to perform Log Operations
	private static final Logger log = LogManager.getLogger(DriverController.class);
	
	//Method for checking the input whether it is a valid input or not
	public void validateInput(Driver driver) {
		if(driver==null|| driver.getLicenseNo()==null || driver.getUsername() == null || driver.getUsername().equals("") || driver.getRating()<1){
			throw new InvalidInputException("Driver Details Cant be null");
		}
		if(!Pattern.compile("[a-zA-Z0-9+_.-]+").matcher(driver.getLicenseNo()).find()){
			throw new InvalidInputException("Enter Valid License No");
		}
		if(!Pattern.compile(".{6}.*").matcher(driver.getPassword()).find()) {
			throw new InvalidInputException("Invalid Password format! Min 6 characters required");
		}
		if(!Pattern.compile("(0/91)?[6-9][0-9]{9}").matcher(driver.getMobileNumber()).find()) {
			throw new InvalidInputException("Enter a valid Mobile Number");
		}
		if(!Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-].[a-zA-Z0-9.-]+$").matcher(driver.getEmail()).find()) {
			throw new InvalidInputException("Enter a valid Email Address");
		}
	}

	// End-point for posting Driver Details into database
	@PostMapping(value="insertDriver")
	@ApiOperation(value = "Used to insertDriver and returns Driver  details")
	public ResponseEntity<Driver> insertDriver(@RequestBody Driver driver) throws DriverAlreadyExistsException {	
		log.info("Controller Triggered");
		validateInput(driver);
		Driver newDriver=iDriverService.insertDriver(driver);
		return new ResponseEntity<Driver>(newDriver, HttpStatus.OK);
				
	}
	
	// End-point for Updating Driver Details into database
	@ApiOperation(value = "Used to updateDriverand returns Driver  details")
	@PutMapping(value="updateDriver")
	public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver) throws DriverNotFoundException {
		log.info("Controller Triggered");
		validateInput(driver);
		Driver updateDriver=iDriverService.updateDriver(driver);
		return new ResponseEntity<Driver>(updateDriver, HttpStatus.OK);
	}
	
	// End-point for deleting Driver Details into database
	@ApiOperation(value = "Used to deleteDriver and returns Driver Details")
	@DeleteMapping("deleteDriver/{driverId}")
	public ResponseEntity<HttpStatus> deleteDriver(@PathVariable Integer driverId) throws CabNotFoundException, DriverNotFoundException {
			log.info("Controller Triggered");
			this.iDriverService.deleteDriver((driverId));
			return new ResponseEntity<>(HttpStatus.OK);
		
	}

	// End-point for getting Driver details with ID
	@ApiOperation(value = "Used to viewDriver and returns Driver  details")
	@GetMapping("driverById/{driverId}")
	public ResponseEntity<Driver> viewDriver(@PathVariable int driverId) throws DriverNotFoundException{
		log.info("Controller Triggered");
		Driver findDriver= iDriverService.viewDriver(driverId);
		return new ResponseEntity<Driver>(findDriver,HttpStatus.OK);
		
	}
	
	// End-point for getting best Drivers
	@ApiOperation(value = "Used to viewBestDrivers() and returns Drivers details")
	@GetMapping("bestDrivers")
	public ResponseEntity<List<Driver>> viewBestDrivers() throws CabNotFoundException, DriverNotFoundException
	{
		log.info("Controller Triggered");
		List<Driver> drivers=iDriverService.viewBestDrivers();
		return new ResponseEntity<List<Driver>>(drivers,HttpStatus.OK);
	}

}
