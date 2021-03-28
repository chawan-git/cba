/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.TripBookingNotFoundException;

//Defining ITripBookingRepository Interface extending JpaRepository which provides CRUD operations 
@Repository
public interface ITripBookingRepository extends JpaRepository<TripBooking, Integer> {

	//performing SQL Query in data base to select all TripBookings taken by customer based upon customerId
	@Query("SELECT trip FROM TripBooking trip, Customer customer WHERE customer.customerId = ?1")
	public List<TripBooking> viewAllTripsCustomer(int customerId);
	@Query("SELECT trip FROM TripBooking trip")
	public List<TripBooking> viewAllTrips() throws TripBookingNotFoundException;
	@Query("SELECT trip FROM TripBooking trip, Customer customer WHERE customer.mobileNumber = ?1")
	public List<TripBooking> viewTripsByCustomerMobileNumber(String mobileNumber);
	@Query("SELECT trip FROM TripBooking trip, Driver driver WHERE driver.mobileNumber = ?1")
	public List<TripBooking> viewTripsByDriverMobileNumber(String mobileNumber);
	@Query("SELECT trip FROM TripBooking trip, Cab cab WHERE cab.carType = ?1")
	public List<TripBooking> viewTripsBycarType(String carType);
	@Query("SELECT trip FROM TripBooking trip WHERE trip.fromDateTime >= ?1 AND trip.toDateTime <= ?2")
	public List<TripBooking> viewAllTripsOnDays(LocalDateTime fromDateTime, LocalDateTime toDateTime);
	@Query("SELECT trip FROM TripBooking trip, Driver driver WHERE driver.driverId = ?1 AND (trip.fromDateTime >= ?2 AND trip.toDateTime <= ?3)")
	public List<TripBooking> viewTripsOnDaysByDriverId(LocalDateTime fromDateTime, LocalDateTime toDateTime, int driverId);
	@Query("SELECT trip FROM TripBooking trip WHERE trip.status = 'Requested'")
	public List<TripBooking> viewTripsRequested();
	@Query("SELECT trip FROM TripBooking trip, Cab cab WHERE cab.carType = ?1 AND (trip.fromDateTime >= ?2 AND trip.toDateTime <= ?3)")
	public List<TripBooking> viewTripsOnDaysBycarType(LocalDateTime fromDateTime, LocalDateTime toDateTime, String carType);
	@Query("SELECT trip FROM TripBooking trip WHERE trip.status = 'DriverAssigned'")
	public List<TripBooking> viewTripsDriverAssigned();
	@Query("SELECT trip FROM TripBooking trip WHERE trip.status = 'TripStarted'")
	public List<TripBooking> viewTripsTripStarted();
	@Query("SELECT trip FROM TripBooking trip WHERE trip.status = 'TripEnded'")
	public List<TripBooking> viewTripsTripEnded();
	@Query("SELECT trip FROM TripBooking trip WHERE trip.status = 'TripPaid'")
	public List<TripBooking> viewTripsTripPaid();
	@Query("SELECT SUM(trip.bill) FROM TripBooking trip, Driver driver WHERE driver.driverId = ?1")
	public Double getTotalRevenueByDriverId(int driverId);
	@Query("SELECT SUM(trip.bill) FROM TripBooking trip, Driver driver WHERE driver.driverId = ?1 AND (trip.fromDateTime >= ?2 AND trip.toDateTime <= ?3)")
	public Double getRevenueOnDaysByDriverId(int driverId, LocalDateTime fromDateTime, LocalDateTime toDateTime);
	
}
