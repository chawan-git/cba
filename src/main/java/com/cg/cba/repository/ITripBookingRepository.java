/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.cba.entities.TripBooking;

//Defining ITripBookingRepository Interface extending JpaRepository which provides CRUD operations 
@Repository
public interface ITripBookingRepository extends JpaRepository<TripBooking, Integer> {

	//performing SQL Query in data base to select all TripBookings taken by customer based upon customerId
	@Query("SELECT trip FROM TripBooking trip, Customer customer WHERE customer.customerId = ?1")
	public List<TripBooking> viewAllTripsCustomer(int customerId);
	
}
