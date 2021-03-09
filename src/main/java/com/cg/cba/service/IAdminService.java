/**
 * 
 */
package com.cg.cba.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cg.cba.entities.Admin;
import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.AdminAlreadyExsistsException;
import com.cg.cba.exception.AdminNotFoundException;
import com.cg.cba.exception.TripBookingNotFoundException;

/**
 * @author arc
 *
 */
public interface IAdminService {
	public Admin insertAdmin(Admin admin) throws AdminAlreadyExsistsException;
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException;
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException;
	public List<TripBooking> getAllTrips(int customerId) throws TripBookingNotFoundException;
	public List<TripBooking> getTripsCabwise() throws TripBookingNotFoundException;
	public List<TripBooking> getTripsCustomerwise() throws TripBookingNotFoundException;
	public List<TripBooking> getTripsDatewise() throws TripBookingNotFoundException; 
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) throws TripBookingNotFoundException;
}
