/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.service;

import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.TripAlreadyExistsException;

import java.util.List;

public interface ITripBookingService {
	
	public TripBooking insertTripBooking(TripBooking tripBooking) throws TripAlreadyExistsException;
	public TripBooking updateTripBooking(TripBooking tripBooking);
	public TripBooking deleteTripBooking(int tripBookingID);
	public List<TripBooking> viewAllTripsCustomer(int tripBookingID);
	public TripBooking calculateBill(int tripBookingId);

}
