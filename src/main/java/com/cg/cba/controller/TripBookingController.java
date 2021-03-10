/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.controller;

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
import com.cg.cba.exception.TripAlreadyExistsException;
import com.cg.cba.exception.TripBookingNotFoundException;
import com.cg.cba.service.ITripBookingService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/tripBooking")
public class TripBookingController {

	@Autowired
	private ITripBookingService tripBookingService;

	@PostMapping(value = "insertTripBooking")
	public ResponseEntity<TripBooking> insertTripBooking(@RequestBody TripBooking tripBooking) throws TripAlreadyExistsException, CustomerNotFoundException, DriverNotFoundException {
		TripBooking tb = tripBookingService.insertTripBooking(tripBooking);
		return new ResponseEntity<TripBooking>(tb, HttpStatus.OK);
	}

	@PutMapping(value = "updateTripBooking")
	public ResponseEntity<TripBooking> updateTripBooking(@RequestBody TripBooking tripBooking) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		TripBooking tb = tripBookingService.updateTripBooking(tripBooking);

		return new ResponseEntity<TripBooking>(tb, HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteTripBooking/{tripBookingId}")
	public ResponseEntity<String> deleteTripBooking(@PathVariable int tripBookingId) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		tripBookingService.deleteTripBooking(tripBookingId);
		return new ResponseEntity<String>("Trip Booking With ID "+tripBookingId+" Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping(value = "viewAllTripsCustomer/{customerId}")
	public ResponseEntity<List<TripBooking>> viewAllTripsCustomer(@PathVariable int customerId) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		List<TripBooking> trips = tripBookingService.viewAllTripsCustomer(customerId);
		return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
	}

	@PutMapping(value = "calculateBill/{tripBookingId}")
	public ResponseEntity<String> calculateBill(@PathVariable int tripBookingId) throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		TripBooking trip = tripBookingService.calculateBill(tripBookingId);
		return new ResponseEntity<String>("The Bill for given Trip Booking ID "+tripBookingId+" is Rs."+trip.getBill()+"/-", HttpStatus.OK);
	}
	
	
}
