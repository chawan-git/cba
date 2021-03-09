/**
 * Author: Bharat Singh
 */

package com.cg.cba.service;


import java.util.List;


import com.cg.cba.entities.Driver;
import com.cg.cba.exception.CabNotFoundException;
import com.cg.cba.exception.DriverAlreadyExistsException;
import com.cg.cba.exception.DriverNotFoundException;

public interface IDriverService {

	// Service methods to be implemented by Service Implementation Class
	

	public Driver viewDriver(int driverId) throws DriverNotFoundException, CabNotFoundException;

	public Driver insertDriver(Driver driver) throws DriverAlreadyExistsException, CabNotFoundException;

	public Driver updateDriver(Driver driver) throws DriverNotFoundException, CabNotFoundException;

	public Driver deleteDriver(int driverId) throws DriverNotFoundException, CabNotFoundException;
	
	public List<Driver> viewBestDrivers() throws DriverNotFoundException, CabNotFoundException;


}
