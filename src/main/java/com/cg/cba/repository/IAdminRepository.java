/**
 * Author: Ashutosh Rao Chawan U
 */
package com.cg.cba.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.cba.entities.Admin;
import com.cg.cba.entities.TripBooking;

/**
 * @author arc
 *
 */

//Admin Repository which connects to the PostgreSQL Database
@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer>{

	//Get All Trips based on Customer ID
	@Query("SELECT trips FROM TripBooking trips, Customer customer WHERE customer.customerId = ?1")
	public List<TripBooking> getAllTrips(int customerId);
	//Get All Trips based on Cab in ascending order
	@Query("SELECT trips FROM TripBooking trips ORDER BY trips.driver")
	public List<TripBooking> getTripsCabwise();
	//Get All Customers based on Customer in ascending order
	@Query("SELECT trips FROM TripBooking trips, Customer customer ORDER BY customer.customerId")
	public List<TripBooking> getTripsCustomerwise();
	//Get All Trips based on Date in ascending order
	@Query("SELECT trips FROM TripBooking trips ORDER BY trips.fromDateTime")
	public List<TripBooking> getTripsDatewise();
	//Get All Trips of a particular customer ID from a particular date & time to a particular date & time.
	@Query("SELECT trips FROM TripBooking trips, Customer customer WHERE customer.customerId = ?1 AND ( trips.fromDateTime >= ?2 AND trips.toDateTime <= ?3 )")
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDateTime, LocalDateTime toDateTime);
	@Query("SELECT admin FROM Admin admin WHERE admin.email = ?1")
	public Admin findByEmail(String email);
	@Query("SELECT admin FROM Admin admin WHERE admin.username = ?1")
	public Admin findByUsername(String username);
	@Query("SELECT admin FROM Admin admin WHERE admin.mobileNumber = ?1")
	public Admin findByMobileNumber(String mobileNumber);
}
