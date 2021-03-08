/**
 * Author: Bharat Singh
 */

package com.cg.cba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cba.entities.Driver;
import com.cg.cba.exception.DriverAlreadyExistsException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.repository.IDriverRepository;

@Service
public class DriverServiceImpl implements IDriverService{


	
	@Autowired 
	private IDriverRepository driverRepository;
	
	
	@Override
	public Driver insertDriver(Driver driver) throws DriverAlreadyExistsException {
		Optional<Driver> driver1 = driverRepository.findById(driver.getDriverId());
		if(driver1.isPresent()) {
			throw new DriverAlreadyExistsException("Driver with ID "+driver.getDriverId()+" already exists!");
		}
		Driver driver2 = driverRepository.save(driver);
		return driver2;
	}
	

	@Override
	public Driver updateDriver(Driver driver) throws DriverNotFoundException {
		Optional<Driver> driver1 = driverRepository.findById(driver.getDriverId());
		if(!driver1.isPresent()) {
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


	@Override
	public Driver deleteDriver(int driverId) throws DriverNotFoundException {
		Optional<Driver> driver1 = driverRepository.findById(driverId);
		if(!driver1.isPresent()) {
			throw new DriverNotFoundException("Driver with ID "+driverId+" not found!");
		}
		Driver driver2= driver1.get();
		driverRepository.delete(driver2);
		return driver2;
		
	}


	@Override
	public Driver viewDriver(int driverId) throws DriverNotFoundException {
		
		return driverRepository.viewDriver(driverId);
	}


	@Override
	public List<Driver> viewBestDrivers() {
		// TODO Auto-generated method stub
		return driverRepository.viewBestDrivers();
	}



}
