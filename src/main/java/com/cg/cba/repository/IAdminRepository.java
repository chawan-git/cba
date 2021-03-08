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
@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer>{
		
	@Query("SELECT trips FROM TripBooking trips WHERE trips.customerId = ?1")
	public List<TripBooking> getAllTrips(int customerId);
	
	@Query("SELECT trips FROM TripBooking trips ORDER BY trips.driver")
	public List<TripBooking> getTripsCabwise();
	
	@Query("SELECT trips FROM TripBooking trips ORDER BY trips.customerId")
	public List<TripBooking> getTripsCustomerwise();
	
	@Query("SELECT trips FROM TripBooking trips ORDER BY trips.fromDateTime")
	public List<TripBooking> getTripsDatewise();
	
	@Query("SELECT trips FROM TripBooking trips WHERE trips.customerId = ?1 AND ( trips.fromDateTime >= ?2 AND trips.toDateTime <= ?3 )")
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate);
}
