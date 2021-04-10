/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","https://cab-test.rao.life","https://cab.rao.life"})
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
		if(!Pattern.compile("[A-Za-z]+").matcher(tripBooking.getFromLocation()).find())
			throw new InvalidInputException("Invalid From Location");
		if(!Pattern.compile("[A-Za-z]+").matcher(tripBooking.getToLocation()).find())
			throw new InvalidInputException("Invalid To Location");
		if(!Pattern.compile("([1-9]+)").matcher(Float.toString(tripBooking.getDistanceInKm())).find())
			throw new InvalidInputException("Invalid distance");		
		
	}
	
	//controller to request insertTripBooking method in service layer
	@ApiOperation(value = "Used to Insert Trip Booking details and returns the Trip Booking details")
	@PostMapping(value = "insertTripBooking")
	public ResponseEntity<TripBooking> insertTripBooking(@RequestBody TripBooking tripBooking) throws TripAlreadyExistsException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("insertTripBooking requested");
//		validateInput(tripBooking);
		TripBooking tb = tripBookingService.insertTripBooking1(tripBooking);
		return new ResponseEntity<TripBooking>(tb, HttpStatus.OK);
	}

	//controller to request updateTripBooking method in service layer
	@ApiOperation(value = "Used to Update Trip Booking details and returns the Trip Booking details")
	@PutMapping(value = "updateTripBooking")
	public ResponseEntity<TripBooking> updateTripBooking(@RequestBody TripBooking tripBooking) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("updateTripBooking request is placed from controller");
//		validateInput(tripBooking);
		TripBooking tb = tripBookingService.updateTripBooking1(tripBooking);
		return new ResponseEntity<TripBooking>(tb, HttpStatus.OK);
	}

	//controller to request deleteTripBooking method in service layer
	@ApiOperation(value = "Used to Delete Trip Booking details and returns the Trip Booking details")
	@DeleteMapping(value = "deleteTripBooking/{tripBookingId}")
	public ResponseEntity<TripBooking> deleteTripBooking(@PathVariable int tripBookingId) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("deleteTripBooking request is placed from controller");
		
		TripBooking tripBooking1 = tripBookingService.deleteTripBooking(tripBookingId);
		return new ResponseEntity<TripBooking>(tripBooking1, HttpStatus.OK);
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
	
	@ApiOperation(value = "Used to View All Trip Booking details and returns the list of Trip Bookings")
	@GetMapping(value = "viewAllTrips")
	public ResponseEntity<List<TripBooking>> viewAllTrips() throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		List<TripBooking> trips = tripBookingService.viewAllTrips();
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to View All Trip Booking details and returns the list of Trip Bookings based on Cusomer MobileNumber")
	@GetMapping(value = "viewTripsByCustomerMobileNumber/{mobileNumber}")
	public ResponseEntity<List<TripBooking>> viewTripsByCustomerMobileNumber(@PathVariable String mobileNumber) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		List<TripBooking> trips = tripBookingService.viewTripsByCustomerMobileNumber(mobileNumber);
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to View All Trip Booking details and returns the list of Trip Bookings based on Driver MobileNumber")
	@GetMapping(value = "viewTripsByDriverMobileNumber/{mobileNumber}")
	public ResponseEntity<List<TripBooking>> viewTripsByDriverMobileNumber(@PathVariable String mobileNumber) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		List<TripBooking> trips = tripBookingService.viewTripsByDriverMobileNumber(mobileNumber);
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}

	@ApiOperation(value = "Used to View All Trip Booking details and returns the list of Trip Bookings based on Cab Type")
	@GetMapping(value = "viewTripsBycarType/{carType}")
	public ResponseEntity<List<TripBooking>> viewTripsBycarType(@PathVariable String carType) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		List<TripBooking> trips = tripBookingService.viewTripsByCarType(carType);
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get All Trips for a time frame")
	@GetMapping(path = "viewAllTripsOnDays")
	public ResponseEntity<List<TripBooking>> viewAllTripsOnDays(@RequestParam(name = "fromDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime, @RequestParam(name = "toDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime){
		log.info("Controller Triggered");
		List<TripBooking> trips = tripBookingService.viewAllTripsOnDays(fromDateTime,toDateTime);
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	@ApiOperation(value = "Get All Trips for a particular driver & particular time frame based on Driver Id")
	@GetMapping(path = "viewTripsOnDaysByDriverId")
	public ResponseEntity<List<TripBooking>> viewTripsOnDaysByDriverId(@RequestParam(name = "fromDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime, @RequestParam(name = "toDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime, @RequestParam(name = "driverId") int driverId){
		log.info("Controller Triggered");
		List<TripBooking> trips = tripBookingService.viewTripsOnDaysByDriverId(fromDateTime,toDateTime, driverId);
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	@ApiOperation(value = "Get All Trips for a particular cab type & particular time frame based on Cab Type")
	@GetMapping(path = "viewTripsOnDaysBycarType")
	public ResponseEntity<List<TripBooking>> viewTripsOnDaysBycarType(@RequestParam(name = "fromDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime, @RequestParam(name = "toDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime, @RequestParam(name = "carType") String carType){
		log.info("Controller Triggered");
		List<TripBooking> trips = tripBookingService.viewTripsOnDaysByCarType(fromDateTime,toDateTime, carType);
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	@ApiOperation(value = "Used to View All Requested Trip Booking details")
	@GetMapping(value = "viewTripsRequested")
	public ResponseEntity<List<TripBooking>> viewTripsRequested() throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		List<TripBooking> trips = tripBookingService.viewTripsRequested();
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to View All DriverAssigned Trip Booking details")
	@GetMapping(value = "viewTripsDriverAssigned")
	public ResponseEntity<List<TripBooking>> viewTripsDriverAssigned() throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		List<TripBooking> trips = tripBookingService.viewTripsDriverAssigned();
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to View All TripStarted Trip Booking details")
	@GetMapping(value = "viewTripsTripStarted")
	public ResponseEntity<List<TripBooking>> viewTripsTripStarted() throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		List<TripBooking> trips = tripBookingService.viewTripsTripStarted();
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to View All TripEnded Trip Booking details")
	@GetMapping(value = "viewTripsTripEnded")
	public ResponseEntity<List<TripBooking>> viewTripsTripEnded() throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		List<TripBooking> trips = tripBookingService.viewTripsTripEnded();
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to View All TripPaid Trip Booking details")
	@GetMapping(value = "viewTripsTripPaid")
	public ResponseEntity<List<TripBooking>> viewTripsTripPaid() throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		List<TripBooking> trips = tripBookingService.viewTripsTripPaid();
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to View Revenue Based on Driver ID")
	@GetMapping(value = "getTotalRevenueByDriverId/{driverId}")
	public ResponseEntity<Double> getTotalRevenueByDriverId(@PathVariable int driverId) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		Double revenue = tripBookingService.getTotalRevenueByDriverId(driverId);
		return new ResponseEntity<Double>(revenue, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to View Revenue Based on Driver ID on particular date frame")
	@GetMapping(value = "getRevenueOnDaysByDriverId")
	public ResponseEntity<Double> getRevenueOnDaysByDriverId(@RequestParam(name = "driverId") int driverId, @RequestParam(name = "fromDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime, @RequestParam(name = "toDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		Double revenue = tripBookingService.getRevenueOnDaysByDriverId(driverId,fromDateTime,toDateTime);
		return new ResponseEntity<Double>(revenue, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to View TripBooking By Id")
	@GetMapping(value = "getTripById/{tripBookingId}")
	public ResponseEntity<TripBooking> getTripById(@PathVariable int tripBookingId) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		
		log.info("Controller Triggered");

		TripBooking trip = tripBookingService.viewTripById(tripBookingId);
		return new ResponseEntity<TripBooking>(trip, HttpStatus.OK);
	}
	
	
}
