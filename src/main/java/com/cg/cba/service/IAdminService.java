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

//Admin Service Interface
public interface IAdminService {
	
	//Method declaration for Insert Admin Service
	public Admin insertAdmin(Admin admin) throws AdminAlreadyExsistsException;
	//Method declaration for Insert Update Service
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException;
	//Method declaration for Insert Delete Service
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException;
	//Method declaration for Insert Get All Trips based on customer Id Service
	public List<TripBooking> getAllTrips(int customerId) throws TripBookingNotFoundException;
	//Method declaration for Insert Get All Trips Cab Wise Service
	public List<TripBooking> getTripsCabwise() throws TripBookingNotFoundException;
	//Method declaration for Insert Get All Trips Customer Wise Service
	public List<TripBooking> getTripsCustomerwise() throws TripBookingNotFoundException;
	//Method declaration for Insert Get All Trips Date Wise Service
	public List<TripBooking> getTripsDatewise() throws TripBookingNotFoundException; 
	//Method declaration for Insert Get All Trips for a particular customer Id, in a particular time frame Service
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) throws TripBookingNotFoundException;
	
	public List<Admin> getAllAdmins() throws AdminNotFoundException;
	
	public Admin getAdminByMobileNumber(String mobileNumber) throws AdminNotFoundException;
	
	public Admin getAdminByUsername(String username) throws AdminNotFoundException;
	
	public Admin getAdminByEmail(String email) throws AdminNotFoundException;
	
	public Admin getAdminById(int adminId) throws AdminNotFoundException;
	
}
