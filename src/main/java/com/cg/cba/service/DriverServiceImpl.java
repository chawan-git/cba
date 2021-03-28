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

import com.cg.cba.entities.Cab;
import com.cg.cba.entities.Driver;
import com.cg.cba.exception.CabNotFoundException;
import com.cg.cba.exception.DriverAlreadyExistsException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.repository.ICabRepository;
import com.cg.cba.repository.IDriverRepository;

//DriverServiceImpl class provides definition to the methods declared in IDriverRepository interface.
@Service
public class DriverServiceImpl implements IDriverService {

	// Creating a Logger Object to perform Log Operations
	private static final Logger log = LogManager.getLogger(DriverServiceImpl.class);

	// loose coupling for driver repository
	@Autowired
	private IDriverRepository driverRepository;

	@Autowired
	private ICabRepository cabRepository;

	// insertDriver method inserts the driver details in to the respective database
	// table.
	@Override
	public Driver insertDriver(Driver driver) throws DriverAlreadyExistsException, CabNotFoundException {
		log.info("Service Triggered");
		Optional<Driver> driver1 = driverRepository.findById(driver.getDriverId()); // Optional object is used to represent null with absent value.
		if (driver1.isPresent()) {
			log.error("Insert Failed! Driver with ID: " + driver.getDriverId() + " already exists!");
			throw new DriverAlreadyExistsException("Driver with ID " + driver.getDriverId() + " already exists!");
		}
		//Checking Cab details before pushing Driver Details to the database
		constraintCheck(driver);
		
		//Checking two Drivers with same License No not Possible
		Driver demoDriver=driverRepository.findByLicense(driver.getLicenseNo());
		if(demoDriver!=null) {
			throw new DriverAlreadyExistsException("Insert failed! Driver With License"+driver.getLicenseNo()+"already Exists!");
		}
		demoDriver = driverRepository.findByEmail(driver.getEmail());
		if(demoDriver != null) {
			throw new DriverAlreadyExistsException("Insert Failed! Driver with Email "+driver.getEmail()+" already exists!");
		}
		demoDriver = driverRepository.findByUsername(driver.getUsername());
		if(demoDriver != null) {
			throw new DriverAlreadyExistsException("Insert Failed! Driver with Username "+driver.getUsername()+" already exists!");
		}
		demoDriver = driverRepository.findByMobileNumber(driver.getMobileNumber());
		if(demoDriver != null) {
			throw new DriverAlreadyExistsException("Insert Failed! Driver with Mobile Number "+driver.getMobileNumber()+" already exists!");
		}
				
		Driver driver2 = driverRepository.save(driver);
		return driver2;
		}

	public Driver insertDriver1(Driver driver) throws DriverAlreadyExistsException, CabNotFoundException {
		log.info("Service Triggered");
		Optional<Driver> driver1 = driverRepository.findById(driver.getDriverId()); // Optional object is used to represent null with absent value.
		if (driver1.isPresent()) {
			log.error("Insert Failed! Driver with ID: " + driver.getDriverId() + " already exists!");
			throw new DriverAlreadyExistsException("Driver with ID " + driver.getDriverId() + " already exists!");
		}
		//Checking Cab details before pushing Driver Details to the database
//		constraintCheck(driver);
		
		//Checking two Drivers with same License No not Possible
		Driver demoDriver=driverRepository.findByLicense(driver.getLicenseNo());
		if(demoDriver!=null) {
			throw new DriverAlreadyExistsException("Insert failed! Driver With License"+driver.getLicenseNo()+"already Exists!");
		}
		demoDriver = driverRepository.findByEmail(driver.getEmail());
		if(demoDriver != null) {
			throw new DriverAlreadyExistsException("Insert Failed! Driver with Email "+driver.getEmail()+" already exists!");
		}
		demoDriver = driverRepository.findByUsername(driver.getUsername());
		if(demoDriver != null) {
			throw new DriverAlreadyExistsException("Insert Failed! Driver with Username "+driver.getUsername()+" already exists!");
		}
		demoDriver = driverRepository.findByMobileNumber(driver.getMobileNumber());
		if(demoDriver != null) {
			throw new DriverAlreadyExistsException("Insert Failed! Driver with Mobile Number "+driver.getMobileNumber()+" already exists!");
		}
				
		Driver driver2 = driverRepository.save(driver);
		return driver2;
		}

	
	// updateDriver method update the driver details in to the respective database table.
	@Override
	public Driver updateDriver(Driver driver) throws DriverNotFoundException, CabNotFoundException {

		log.info("Service Triggered");
		Optional<Driver> driver1 = driverRepository.findById(driver.getDriverId());
		if (!driver1.isPresent()) {
			log.error("Update Failed! Driver with ID: " + driver.getDriverId() + " not found!");
			throw new DriverNotFoundException("Driver with ID " + driver.getDriverId() + " not found!");
		}
		
		//Checking Cab details before pushing Driver Details to the database
		constraintCheck(driver);

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

	public Driver updateDriver1(Driver driver) throws DriverNotFoundException, CabNotFoundException {

		log.info("Service Triggered");
		Optional<Driver> driver1 = driverRepository.findById(driver.getDriverId());
		if (!driver1.isPresent()) {
			log.error("Update Failed! Driver with ID: " + driver.getDriverId() + " not found!");
			throw new DriverNotFoundException("Driver with ID " + driver.getDriverId() + " not found!");
		}
		
		//Checking Cab details before pushing Driver Details to the database
//		constraintCheck(driver);

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
	
	// deleteDriver method deletes the driver details from the respective database
	// table.
	@Override
	public Driver deleteDriver(int driverId) throws DriverNotFoundException {
		log.info("Service Triggered");
		Optional<Driver> driver1 = driverRepository.findById(driverId);
		if (!driver1.isPresent()) {
			log.error("Delete Failed! Driver with ID: " + driverId + " not found!");
			throw new DriverNotFoundException("Driver with ID " + driverId + " not found!");
		}
		Driver driver2 = driver1.get();
		driverRepository.delete(driver2);
		return driver2;

	}

	// viewDriver method views the driver details according to the id provided from
	// the respective database table.
	@Override
	public Driver viewDriver(int driverId) throws DriverNotFoundException {
		log.info("Service Triggered");
		Optional<Driver> driver1 = driverRepository.findById(driverId);
		if (!driver1.isPresent()) {
			log.error("Viewing Failed! Driver with ID: " + driverId + " not found!");
			throw new DriverNotFoundException("Driver Not Found!");
		}
		Driver driver2 = driver1.get();
		return driver2;
	}

	// viewBestDriver method views the driver details having Rating more than 4.5
	// from the respective database table.
	@Override
	public List<Driver> viewBestDrivers() throws DriverNotFoundException {
		log.info("Service Triggered");{
		List<Driver> drivers = driverRepository.viewBestDrivers();
		if(drivers.isEmpty()) {
			log.error("viewBestDriver Failed!");
			throw new DriverNotFoundException("No Best Driver Found!");}
		return drivers;
			}

	}
	
	// Method to check Cab details before Performing methods that include cab details
	public void constraintCheck(Driver driver) {
		//checkingCab(driver);
		Optional<Cab> cab1 = cabRepository.findById(driver.getCab().getCabId());		
		Cab cab = cab1.orElseThrow(()->new CabNotFoundException("Invalid cab Id"));
		if(
				 
				!cab.getCarType().equals(driver.getCab().getCarType()) || 
				cab.getPerKmRate() != driver.getCab().getPerKmRate() 
				) 
		{
			throw new CabNotFoundException("Cab details not correct");
		}	
						
	}

	@Override
	public List<Driver> viewAllDrivers() throws DriverNotFoundException, CabNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		List<Driver> drivers = driverRepository.viewAllDrivers();
		if (drivers.isEmpty()) {
			log.error("Viewing Failed! Driver not found!");
			throw new DriverNotFoundException("Driver Not Found!");
		}
		return drivers;
	}

	@Override
	public List<Driver> viewAvailableDrivers() throws DriverNotFoundException, CabNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		List<Driver> drivers = driverRepository.viewAvailableDrivers();
		if (drivers.isEmpty()) {
			log.error("Viewing Failed! Driver not found!");
			throw new DriverNotFoundException("Driver Not Found!");
		}
		return drivers;
	}

	@Override
	public List<Driver> viewOnTripDrivers() throws DriverNotFoundException, CabNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		List<Driver> drivers = driverRepository.viewOnTripDrivers();
		if (drivers.isEmpty()) {
			log.error("Viewing Failed! Driver not found!");
			throw new DriverNotFoundException("Driver Not Found!");
		}
		return drivers;
	}

	@Override
	public List<Driver> viewNotAvailableDrivers() throws DriverNotFoundException, CabNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		List<Driver> drivers = driverRepository.viewNotAvailableDrivers();
		if (drivers.isEmpty()) {
			log.error("Viewing Failed! Driver not found!");
			throw new DriverNotFoundException("Driver Not Found!");
		}
		return drivers;
	}

	@Override
	public Driver viewDriverByMobileNumber(String mobileNumber) throws DriverNotFoundException, CabNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		Driver driver1 = driverRepository.findByMobileNumber(mobileNumber);
		if (driver1.equals(null)) {
			log.error("Viewing Failed! Driver with mobileNumber: " + mobileNumber + " not found!");
			throw new DriverNotFoundException("Driver Not Found!");
		}
		return driver1;
	}

	@Override
	public Driver viewDriverByUsername(String username) throws DriverNotFoundException, CabNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		Driver driver1 = driverRepository.findByUsername(username);
		if (driver1.equals(null)) {
			log.error("Viewing Failed! Driver with username: " + username + " not found!");
			throw new DriverNotFoundException("Driver Not Found!");
		}
		return driver1;
	}

	@Override
	public Driver viewDriverByEmail(String email) throws DriverNotFoundException, CabNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		Driver driver1 = driverRepository.findByEmail(email);
		if (driver1.equals(null)) {
			log.error("Viewing Failed! Driver with email: " + email + " not found!");
			throw new DriverNotFoundException("Driver Not Found!");
		}
		return driver1;
	}
}