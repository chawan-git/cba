/**
 * Author: Bharat Singh
 */

package com.cg.cba.controller;



import java.util.List;

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
import com.cg.cba.service.IDriverService;

@RestController
@RequestMapping(path = "api/v1/driver")
public class DriverController {
	
	@Autowired
	IDriverService iDriverService;
	
		
	@PostMapping(value="insertDriver")
	public ResponseEntity<Driver> insertDriver(@RequestBody Driver driver) throws DriverAlreadyExistsException {	
		Driver newDriver=iDriverService.insertDriver(driver);
		return new ResponseEntity<Driver>(newDriver, HttpStatus.OK);
				
	}
	
	@PutMapping(value="updateDriver")
	public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver) throws DriverNotFoundException {
		Driver updateDriver=iDriverService.updateDriver(driver);
		return new ResponseEntity<Driver>(updateDriver, HttpStatus.OK);
	}
		
	@DeleteMapping("deleteDriver/{driverId}")
	public ResponseEntity<HttpStatus> deleteDriver(@PathVariable Integer driverId) throws CabNotFoundException, DriverNotFoundException {
		
			this.iDriverService.deleteDriver((driverId));
			return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@GetMapping("driverById/{driverId}")
	public ResponseEntity<Driver> viewDriver(@PathVariable int driverId) throws DriverNotFoundException{
		Driver findDriver= iDriverService.viewDriver(driverId);
		return new ResponseEntity<Driver>(findDriver,HttpStatus.OK);
		
	}
	
	@GetMapping("bestDrivers")
	public ResponseEntity<List<Driver>> viewBestDrivers() throws CabNotFoundException, DriverNotFoundException
	{
		List<Driver> drivers=iDriverService.viewBestDrivers();
		return new ResponseEntity<List<Driver>>(drivers,HttpStatus.OK);
	}

}
