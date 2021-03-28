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

	public Driver insertDriver1(Driver driver) throws DriverAlreadyExistsException, CabNotFoundException;

	public Driver updateDriver(Driver driver) throws DriverNotFoundException, CabNotFoundException;

	public Driver updateDriver1(Driver driver) throws DriverNotFoundException, CabNotFoundException;

	public Driver deleteDriver(int driverId) throws DriverNotFoundException, CabNotFoundException;
	
	public List<Driver> viewBestDrivers() throws DriverNotFoundException, CabNotFoundException;

	public List<Driver> viewAllDrivers() throws DriverNotFoundException, CabNotFoundException;
	
	public List<Driver> viewAvailableDrivers() throws DriverNotFoundException,CabNotFoundException;
	
	public List<Driver> viewOnTripDrivers() throws DriverNotFoundException,CabNotFoundException;
	
	public List<Driver> viewNotAvailableDrivers() throws DriverNotFoundException,CabNotFoundException;
	
	public Driver viewDriverByMobileNumber(String mobileNumber) throws DriverNotFoundException,CabNotFoundException;
	
	public Driver viewDriverByUsername(String username) throws DriverNotFoundException,CabNotFoundException;
	
	public Driver viewDriverByEmail(String email) throws DriverNotFoundException,CabNotFoundException;
}
