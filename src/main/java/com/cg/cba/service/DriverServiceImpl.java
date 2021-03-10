/**
 * Author: Bharat Singh
 */

package com.cg.cba.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cba.entities.Driver;
import com.cg.cba.exception.CabNotFoundException;
import com.cg.cba.exception.DriverAlreadyExistsException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.repository.IDriverRepository;


//DriverServiceImpl class provides definition to the methods declared in IDriverRepository interface.
@Service
public class DriverServiceImpl implements IDriverService{

	//Creating a Logger Object to perform Log Operations
	private static final Logger log = LogManager.getLogger(DriverServiceImpl.class);
	
	//loose coupling for driver repository
	@Autowired 
	private IDriverRepository driverRepository;
	
	
	// insertDriver method inserts the driver details in to the respective database table.
	@Override
	public Driver insertDriver(Driver driver) throws DriverAlreadyExistsException, CabNotFoundException {
		log.info("Service Triggered");
		Optional<Driver> driver1 = driverRepository.findById(driver.getDriverId());
		if(driver1.isPresent()) {
			log.error("Insert Failed! Driver with ID: "+driver.getDriverId()+" already exists!");
			throw new DriverAlreadyExistsException("Driver with ID "+driver.getDriverId()+" already exists!");
		}
		Driver driver2 = driverRepository.save(driver);
		return driver2;
	}
	
	// updateDriver method update the driver details in to the respective database table.
	@Override
	public Driver updateDriver(Driver driver) throws DriverNotFoundException, CabNotFoundException {
		
		log.info("Service Triggered");
		Optional<Driver> driver1 = driverRepository.findById(driver.getDriverId());
		if(!driver1.isPresent()) {
			log.error("Update Failed! Driver with ID: "+driver.getDriverId()+" not found!");
			throw new DriverNotFoundException("Driver with ID "+driver.getDriverId()+" not found!");
		}
		Driver driver2 = driver1.get();
		driver2.setAddress(driver.getAddress());
		driver2.setCab(driver.getCab());
		driver2.setEmail(driver.getEmail());
		driver2.setLicenseNo(driver.getLicenseNo());
		driver2.setMobileNumber(driver.getMobileNumber());
		driver2.setPassword(driver.getPassword());
		driver2.setRating(driver.getRating());
		driver2.setUsername(driver.getUsername());
		
		driverRepository.save(driver2);
		return driver2;
	}

	// deleteDriver method deletes the driver details from the respective database table.
	@Override
	public Driver deleteDriver(int driverId) throws DriverNotFoundException, CabNotFoundException {
		log.info("Service Triggered");
		Optional<Driver> driver1 = driverRepository.findById(driverId);
		if(!driver1.isPresent()) {
			log.error("Delete Failed! Driver with ID: "+driverId+" not found!");
			throw new DriverNotFoundException("Driver with ID "+driverId+" not found!");
		}
		Driver driver2= driver1.get();
		driverRepository.delete(driver2);
		return driver2;
		
	}

	// viewDriver method views  the driver details according to the id provided from the respective database table.
	@Override
	public Driver viewDriver(int driverId) throws DriverNotFoundException, CabNotFoundException {
		log.info("Service Triggered");
		Driver driver =  driverRepository.viewDriver(driverId);
		if(driver.getDriverId() < 1) {
			log.error("Viewing Failed! Driver with ID: "+driverId+" not found!");
			throw new DriverNotFoundException("Driver Not Found!");}
		return driver;
	}

	// viewBestDriver method views the driver details having Rating more than 4.5 from the respective database table.
	@Override
	public List<Driver> viewBestDrivers() throws DriverNotFoundException, CabNotFoundException {
		log.info("Service Triggered");{
		List<Driver> drivers = driverRepository.viewBestDrivers();
		if(drivers.isEmpty()) {
			log.error("viewBestDriver Failed!");
			throw new DriverNotFoundException("No Best Driver Found!");}
		return drivers;
	}



}
}