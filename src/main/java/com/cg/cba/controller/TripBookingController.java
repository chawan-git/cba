/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.controller;

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

import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.exception.InvalidInputException;
import com.cg.cba.exception.TripAlreadyExistsException;
import com.cg.cba.exception.TripBookingNotFoundException;
import com.cg.cba.service.ITripBookingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "api/v1/tripBooking")
@Api(description = "REST API of TripBooking Table")
public class TripBookingController {

	//Creating a Logger Object to perform Log Operations
	private final static Logger log = LogManager.getLogger(TripBookingController.class);
	
	@Autowired
	private ITripBookingService tripBookingService;

	//method to validate input data
	public void validateInput(TripBooking tripBooking)
	{
		if(!Pattern.compile("[a-z]").matcher(tripBooking.getFromLocation()).find())
			throw new InvalidInputException("Invalid From Loaction");
		if(!Pattern.compile("[A-Za-z]").matcher(tripBooking.getToLocation()).find())
			throw new InvalidInputException("Invalid To Loaction");
		if(!Pattern.compile("([1-9]+)").matcher(Float.toString(tripBooking.getDistanceInKm())).find())
			throw new InvalidInputException("Invalid To Loaction");		
		
	}
	
	//controller to request insertTripBooking method in service layer
	@ApiOperation(value = "Used to Insert Trip Booking details and returns the Trip Booking details")
	@PostMapping(value = "insertTripBooking")
	public ResponseEntity<TripBooking> insertTripBooking(@RequestBody TripBooking tripBooking) throws TripAlreadyExistsException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("insertTripBooking requested");
		validateInput(tripBooking);
		TripBooking tb = tripBookingService.insertTripBooking(tripBooking);
		return new ResponseEntity<TripBooking>(tb, HttpStatus.OK);
	}

	//controller to request updateTripBooking method in service layer
	@ApiOperation(value = "Used to Update Trip Booking details and returns the Trip Booking details")
	@PutMapping(value = "updateTripBooking")
	public ResponseEntity<TripBooking> updateTripBooking(@RequestBody TripBooking tripBooking) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("updateTripBooking request is placed from controller");
		validateInput(tripBooking);
		TripBooking tb = tripBookingService.updateTripBooking(tripBooking);
		return new ResponseEntity<TripBooking>(tb, HttpStatus.OK);
	}

	//controller to request deleteTripBooking method in service layer
	@ApiOperation(value = "Used to Delete Trip Booking details and returns the Trip Booking details")
	@DeleteMapping(value = "deleteTripBooking/{tripBookingId}")
	public ResponseEntity<String> deleteTripBooking(@PathVariable int tripBookingId) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("deleteTripBooking request is placed from controller");
		
		tripBookingService.deleteTripBooking(tripBookingId);
		return new ResponseEntity<String>("Trip Booking With ID "+tripBookingId+" Deleted Successfully", HttpStatus.OK);
	}

	//controller to request viewAllTripsCustomer method in service layer
	@ApiOperation(value = "Used to View All Trip Booking details and returns the list of Trip Bookings")
	@GetMapping(value = "viewAllTripsCustomer/{customerId}")
	public ResponseEntity<List<TripBooking>> viewAllTripsCustomer(@PathVariable int customerId) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("viewAllTripsCustomer request is placed from controller");

		List<TripBooking> trips = tripBookingService.viewAllTripsCustomer(customerId);
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}

	//controller to request calculateBill method in service layer
	@ApiOperation(value = "Used to Calculate Bill for Trip Booking and returns Trip Booking details")
	@PutMapping(value = "calculateBill/{tripBookingId}")
	public ResponseEntity<String> calculateBill(@PathVariable int tripBookingId) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("calculateBill request is placed from controller");
		
		TripBooking trip = tripBookingService.calculateBill(tripBookingId);
		return new ResponseEntity<String>("The Bill for given Trip Booking ID "+tripBookingId+" is Rs."+trip.getBill()+"/-", HttpStatus.OK);
	}
	
	
}
