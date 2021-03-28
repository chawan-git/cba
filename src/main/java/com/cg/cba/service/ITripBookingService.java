/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.service;

import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.exception.TripAlreadyExistsException;
import com.cg.cba.exception.TripBookingNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface ITripBookingService {
	
	//Defining all the methods required which will be implemented in the ITripBookingServiceImpl class
	
	public TripBooking insertTripBooking(TripBooking tripBooking) throws TripAlreadyExistsException,CustomerNotFoundException, DriverNotFoundException;
	public TripBooking updateTripBooking(TripBooking tripBooking) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public TripBooking insertTripBooking1(TripBooking tripBooking) throws TripAlreadyExistsException,CustomerNotFoundException, DriverNotFoundException;
	public TripBooking updateTripBooking1(TripBooking tripBooking) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	
	public TripBooking deleteTripBooking(int tripBookingID) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewAllTripsCustomer(int tripBookingID) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public TripBooking calculateBill(int tripBookingId) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;

	public List<TripBooking> viewAllTrips() throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewTripsByCustomerMobileNumber(String mobileNumber) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewTripsByDriverMobileNumber(String mobileNumber) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewTripsByCarType(String carType) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewAllTripsOnDays(LocalDateTime fromDateTime, LocalDateTime toDateTime) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewTripsOnDaysByDriverId(LocalDateTime fromDateTime, LocalDateTime toDateTime, int driverId) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewTripsRequested() throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewTripsOnDaysByCarType(LocalDateTime fromDateTime, LocalDateTime toDateTime, String carType) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewTripsDriverAssigned() throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewTripsTripStarted() throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewTripsTripEnded() throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public List<TripBooking> viewTripsTripPaid() throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public Double getTotalRevenueByDriverId(int driverId) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
	public Double getRevenueOnDaysByDriverId(int driverId, LocalDateTime fromDateTime, LocalDateTime toDateTime) throws TripBookingNotFoundException,CustomerNotFoundException, DriverNotFoundException;
}
