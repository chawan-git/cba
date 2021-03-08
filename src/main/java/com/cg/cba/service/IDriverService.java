/**
 * Author: Bharat Singh
 */

package com.cg.cba.service;


import java.util.List;


import com.cg.cba.entities.Driver;
import com.cg.cba.exception.DriverAlreadyExistsException;
import com.cg.cba.exception.DriverNotFoundException;

public interface IDriverService {

	// Service methods to be implemented by Service Implementation Class
	

	public Driver viewDriver(int driverId) throws DriverNotFoundException;

	public Driver insertDriver(Driver driver) throws DriverAlreadyExistsException;

	public Driver updateDriver(Driver driver) throws DriverNotFoundException;

	public Driver deleteDriver(int driverId) throws DriverNotFoundException;
	
	public List<Driver> viewBestDrivers();


}
