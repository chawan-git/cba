/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cba.entities.Cab;
import com.cg.cba.entities.Customer;
import com.cg.cba.entities.Driver;
import com.cg.cba.entities.TripBooking;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

import com.cg.cba.exception.CabNotFoundException;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.exception.TripAlreadyExistsException;
import com.cg.cba.exception.TripBookingNotFoundException;
import com.cg.cba.repository.ICabRepository;
import com.cg.cba.repository.ICustomerRepository;
import com.cg.cba.repository.IDriverRepository;
import com.cg.cba.repository.ITripBookingRepository;

//service class implementing all the methods of ITripBookingService
@Service
public class TripBookingServiceImpl implements ITripBookingService {
		
	//Creating a Logger Object to perform Log Operations
	private final static Logger log = LogManager.getLogger(TripBookingServiceImpl.class);
	
	//Performing injections to a variables
	@Autowired
	private ITripBookingRepository tripBookingRepository;
	@Autowired
	private IDriverRepository driverRepository;
	@Autowired
	private ICabRepository cabRepository;
	@Autowired
	private ICustomerRepository customerRepository; 

	
	
	// Method to insert a TripBooking and returns inserted TripBooking Object
	@Override
	public TripBooking insertTripBooking(TripBooking tripBooking) throws TripAlreadyExistsException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("insertTripBooking service got excuted");
		
		//Exception Handling
		if(tripBooking == null) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Empty Trip Booking Fields detected");
		}
		Optional<TripBooking> tripBooking1 = tripBookingRepository.findById(tripBooking.getTripBookingId());
		if(tripBooking1.isPresent()) {
			throw new TripAlreadyExistsException("Trip with ID "+tripBooking.getTripBookingId()+" already exists");
		}
		
		//checking the details of customer, driver and cab provided
		constraintCheck(tripBooking);
		
		if(tripBooking.getBill()==0)
		{
			float perKmRate = tripBooking.getDriver().getCab().getPerKmRate();
			float distance = tripBooking.getDistanceInKm();				
			tripBooking.setBill(distance*perKmRate);
		}
		
		//saving to database
		TripBooking tb = tripBookingRepository.save(tripBooking);
		return tb;
	}
	
	public TripBooking insertTripBooking1(TripBooking tripBooking) throws TripAlreadyExistsException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("insertTripBooking service got excuted");
		
		//Exception Handling
		if(tripBooking == null) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Empty Trip Booking Fields detected");
		}
		Optional<TripBooking> tripBooking1 = tripBookingRepository.findById(tripBooking.getTripBookingId());
		if(tripBooking1.isPresent()) {
			throw new TripAlreadyExistsException("Trip with ID "+tripBooking.getTripBookingId()+" already exists");
		}
		
		//checking the details of customer, driver and cab provided
//		constraintCheck(tripBooking);
		
		if(tripBooking.getBill()==0)
		{
			float perKmRate = tripBooking.getDriver().getCab().getPerKmRate();
			float distance = tripBooking.getDistanceInKm();				
			tripBooking.setBill(distance*perKmRate);
		}
		
		//saving to database
		TripBooking tb = tripBookingRepository.save(tripBooking);
		return tb;
	}

	
	
	// Method to update a TripBooking using tripBookingID  and returns updated TripBooking Object	
	@Override
	public TripBooking updateTripBooking(TripBooking tripBooking) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException {
		
		log.info("updateTripBooking service got excuted");
		
		//Exception Handling
		if(tripBooking.getTripBookingId() == 0) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Empty Trip Booking Fields detected");
		}
		Optional<TripBooking> tripBooking1 = tripBookingRepository.findById(tripBooking.getTripBookingId());
		if(!tripBooking1.isPresent() || tripBooking == null) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip with ID "+tripBooking.getTripBookingId()+" not found.");
		}
		TripBooking tripBooking2 = tripBooking1.get();
		
		// update section
		tripBooking2.setCustomer(tripBooking.getCustomer());
		tripBooking2.setDriver(tripBooking.getDriver());
		tripBooking2.setFromLocation(tripBooking.getFromLocation());
		tripBooking2.setToLocation(tripBooking.getToLocation());
		tripBooking2.setFromDateTime(tripBooking.getFromDateTime());
		tripBooking2.setToDateTime(tripBooking.getToDateTime());
		tripBooking2.setStatus(tripBooking.getStatus());
		tripBooking2.setDistanceInKm(tripBooking.getDistanceInKm());
		tripBooking2.setBill(tripBooking.getBill());
		
		//checking the details of customer, driver and cab provided
		constraintCheck(tripBooking);
		
		if(tripBooking.getBill()==0)
		{
			float perKmRate = tripBooking.getDriver().getCab().getPerKmRate();
			float distance = tripBooking.getDistanceInKm();				
			tripBooking2.setBill(distance*perKmRate);
		}
		
		//saving to database
		tripBookingRepository.save(tripBooking2);
		return tripBooking2;
	}

	public TripBooking updateTripBooking1(TripBooking tripBooking) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException {
		
		log.info("updateTripBooking service got excuted");
		
		//Exception Handling
		if(tripBooking.getTripBookingId() == 0) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Empty Trip Booking Fields detected");
		}
		Optional<TripBooking> tripBooking1 = tripBookingRepository.findById(tripBooking.getTripBookingId());
		if(!tripBooking1.isPresent() || tripBooking == null) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip with ID "+tripBooking.getTripBookingId()+" not found.");
		}
		TripBooking tripBooking2 = tripBooking1.get();
		
		// update section
		tripBooking2.setCustomer(tripBooking.getCustomer());
		tripBooking2.setDriver(tripBooking.getDriver());
		tripBooking2.setFromLocation(tripBooking.getFromLocation());
		tripBooking2.setToLocation(tripBooking.getToLocation());
		tripBooking2.setFromDateTime(tripBooking.getFromDateTime());
		tripBooking2.setToDateTime(tripBooking.getToDateTime());
		tripBooking2.setStatus(tripBooking.getStatus());
		tripBooking2.setDistanceInKm(tripBooking.getDistanceInKm());
		tripBooking2.setBill(tripBooking.getBill());
		
		//checking the details of customer, driver and cab provided
//		constraintCheck(tripBooking);
		
		if(tripBooking.getBill()==0)
		{
			float perKmRate = tripBooking.getDriver().getCab().getPerKmRate();
			float distance = tripBooking.getDistanceInKm();				
			tripBooking2.setBill(distance*perKmRate);
		}
		
		//saving to database
		tripBookingRepository.save(tripBooking2);
		return tripBooking2;
	}
	
	//Method to Delete a TripBooking using tripBookingID  and returns same TripBooking Object
	@Override
	public TripBooking deleteTripBooking(int tripBookingId) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException {
		
		log.info("deleteTripBooking method got excuted from service layer");

		//Exception handling
		if(tripBookingId==0) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking ID cannot be empty or 0");
		}
		Optional<TripBooking> tripBooking1 = tripBookingRepository.findById(tripBookingId);
		if(!tripBooking1.isPresent()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip with ID "+tripBookingId+" not found.");
		}
		TripBooking tripBooking2 = tripBooking1.get();
		
		//Saving to database
		tripBookingRepository.delete(tripBooking2);
		return tripBooking2;
	}

	//Method to view list of TripBookings taken by a customer and returns list of TripBooking Objects
	@Override
	public List<TripBooking> viewAllTripsCustomer(int customerId) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException {
		
		log.info("viewAllTripsCustomer method got excuted from service layer");
		
		List<TripBooking> trips = tripBookingRepository.viewAllTripsCustomer(customerId);
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	
	//Method to calculate bill of TripBooking using tripBookingID and returns same TripBooking Object
	@Override
	public TripBooking calculateBill(int tripBookingId) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException {
		
		log.info("calculateBill method got excuted from service layer");

		//Exception Handling
		if(tripBookingId==0)
		{
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking ID cannot be empty or 0");
		}
		
		Optional<TripBooking> tripBooking1 = tripBookingRepository.findById(tripBookingId);
		if(!tripBooking1.isPresent()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip with ID "+tripBookingId+" not found.");
		}
		TripBooking tripBooking2 = tripBooking1.get();
		
		float perKmRate = tripBooking2.getDriver().getCab().getPerKmRate();
		float distance = tripBooking2.getDistanceInKm();				
		tripBooking2.setBill(distance*perKmRate);	
		tripBookingRepository.save(tripBooking2);	
		return tripBooking2;
		
	}
	
	
	
	//checking the details of customer, driver and cab provided
	public void constraintCheck(TripBooking tripBooking)
	{
		//checkingCab(tripBooking);
		if(tripBooking.getDriver().getCab() == null)
		{
			throw new CabNotFoundException("cab details not found");
		}
		
		Optional<Cab> cab1 = cabRepository.findById(tripBooking.getDriver().getCab().getCabId());		
		Cab cab = cab1.orElseThrow(()->new CustomerNotFoundException("Invalid cab Id"));
		
		if(
				!cab.getCarType().equals(tripBooking.getDriver().getCab().getCarType()) || 
				cab.getPerKmRate() != tripBooking.getDriver().getCab().getPerKmRate() 
				) 
		{
			throw new CabNotFoundException("cab with given details does not exist");
		}
		
		//checkingCustomer(tripBooking);
		if(tripBooking.getCustomer() == null)
		{
			log.error("CustomerNotFoundException");
			throw new CustomerNotFoundException("customer with given details not found");
		}
		
		Customer customer = customerRepository.findById(tripBooking.getCustomer().getCustomerId()).get();
		
		if(
				customer.getCustomerId()!= tripBooking.getCustomer().getCustomerId() ||
				!customer.getUsername().equals(tripBooking.getCustomer().getUsername()) ||
				!customer.getPassword().equals(tripBooking.getCustomer().getPassword()) ||
				!customer.getMobileNumber().equals(tripBooking.getCustomer().getMobileNumber()) ||
				!customer.getEmail().equals(tripBooking.getCustomer().getEmail()) ||
				!customer.getAddress().equals(tripBooking.getCustomer().getAddress())
				)
		{
			log.error("CustomerNotFoundException");
			throw new CustomerNotFoundException("customer with given details does not exist");	
		}
		
		//checkingDriver(tripBooking);
		if(tripBooking.getDriver() == null)
		{
			log.error("DriverNotFoundException");
			throw new CustomerNotFoundException("Driver with given details not found");
		}
		Driver driver = driverRepository.findById(tripBooking.getDriver().getDriverId()).get();
		
		if(
				driver.getDriverId()!= tripBooking.getDriver().getDriverId() ||
				!driver.getUsername().equals(tripBooking.getDriver().getUsername()) ||
				!driver.getPassword().equals(tripBooking.getDriver().getPassword()) ||
				!driver.getMobileNumber().equals(tripBooking.getDriver().getMobileNumber()) ||
				!driver.getEmail().equals(tripBooking.getDriver().getEmail()) ||
				!driver.getLicenseNo().equals(tripBooking.getDriver().getLicenseNo()) ||
				driver.getRating() != tripBooking.getDriver().getRating() ||
				!driver.getAddress().equals(tripBooking.getDriver().getAddress())
				) {
			log.error("DriverNotFoundException");
			throw new DriverNotFoundException("driver with given details does not exist");
		}
				
	}

	@Override
	public List<TripBooking> viewAllTrips()
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewAllTrips();
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewTripsByCustomerMobileNumber(String mobileNumber)
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewTripsByCustomerMobileNumber(mobileNumber);
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewTripsByDriverMobileNumber(String mobileNumber)
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewTripsByDriverMobileNumber(mobileNumber);
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewTripsByCarType(String carType)
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewTripsBycarType(carType);
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewAllTripsOnDays(LocalDateTime fromDateTime, LocalDateTime toDateTime)
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewAllTripsOnDays(fromDateTime, toDateTime);
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewTripsOnDaysByDriverId(LocalDateTime fromDateTime, LocalDateTime toDateTime,
			int driverId) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewTripsOnDaysByDriverId(fromDateTime, toDateTime, driverId);
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewTripsRequested()
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewTripsRequested();
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewTripsOnDaysByCarType(LocalDateTime fromDateTime, LocalDateTime toDateTime,
			String carType) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewTripsOnDaysBycarType(fromDateTime, toDateTime, carType);
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewTripsDriverAssigned()
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewTripsDriverAssigned();
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewTripsTripStarted()
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewTripsTripStarted();
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewTripsTripEnded()
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewTripsTripEnded();
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public List<TripBooking> viewTripsTripPaid()
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		List<TripBooking> trips = tripBookingRepository.viewTripsTripPaid();
		if(trips.isEmpty()) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return trips;
	}

	@Override
	public Double getTotalRevenueByDriverId(int driverId)
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		double revenue = tripBookingRepository.getTotalRevenueByDriverId(driverId);
		if(revenue<=0) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return revenue;
	}

	@Override
	public Double getRevenueOnDaysByDriverId(int driverId, LocalDateTime fromDateTime, LocalDateTime toDateTime)
			throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service triggered");
		
		double revenue = tripBookingRepository.getRevenueOnDaysByDriverId(driverId,fromDateTime,toDateTime);
		if(revenue<=0) {
			log.error("TripBookingNotFoundException");
			throw new TripBookingNotFoundException("Trip Booking Not Found!");
		}
		return revenue;
	}
	
}
