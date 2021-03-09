/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.cba.entities.TripBooking;

@Repository
public interface ITripBookingRepository extends JpaRepository<TripBooking, Integer> {

	@Query("SELECT trip FROM TripBooking trip, Customer customer WHERE customer.customerId = ?1")
	public List<TripBooking> viewAllTripsCustomer(int customerId);
	
}
