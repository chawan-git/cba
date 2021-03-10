/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.service;

import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.exception.TripAlreadyExistsException;
import com.cg.cba.exception.TripBookingNotFoundException;

import java.util.List;

public interface ITripBookingService {
	
	//Defining all the methods required which will be implemented in the ITripBookingServiceImpl class
	
	public TripBooking insertTripBooking(TripBooking tripBooking) throws TripAlreadyExistsException,CustomerNotFoundException, DriverNotFoundException;
	public TripBooking updateTripBooking(TripBooking tripBooking) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public TripBooking deleteTripBooking(int tripBookingID) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;;
	public List<TripBooking> viewAllTripsCustomer(int tripBookingID) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;;
	public TripBooking calculateBill(int tripBookingId) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;;

}
