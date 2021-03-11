/**
 * 
 */
package com.cg.cba.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cba.entities.Admin;
import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.AdminAlreadyExsistsException;
import com.cg.cba.exception.AdminNotFoundException;
import com.cg.cba.exception.TripBookingNotFoundException;
import com.cg.cba.repository.IAdminRepository;

/**
 * @author arc
 *
 */

//Admin Service Interface Implementation
@Service
public class AdminServiceImpl implements IAdminService {
	
	//Logger Initialisation
	private static final Logger log = LogManager.getLogger(AdminServiceImpl.class);

	//Admin Repository Loose Coupling to perform tasks on the database
	@Autowired
	private IAdminRepository adminRepository;
	
	//Service Method Implementation for inserting an Admin
	@Override
	public Admin insertAdmin(Admin admin) throws AdminAlreadyExsistsException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		
		Optional<Admin> admin1 = adminRepository.findById(admin.getAdminId());
		if(admin1.isPresent()) {
			log.error("Insert Failed! Admin with ID: "+admin.getAdminId()+" already exists!");
			throw new AdminAlreadyExsistsException("Insert Failed! Admin with ID: "+admin.getAdminId()+" already exists!");
		}
		
		Admin admin2 = adminRepository.findByEmail(admin.getEmail());
		if(admin2 != null) {
			throw new AdminAlreadyExsistsException("Insert Failed! Admin with Email "+admin.getEmail()+" already exists!");
		}
		admin2 = adminRepository.findByUsername(admin.getUsername());
		if(admin2 != null) {
			throw new AdminAlreadyExsistsException("Insert Failed! Admin with Username "+admin.getUsername()+" already exists!");
		}
		admin2 = adminRepository.findByMobileNumber(admin.getMobileNumber());
		if(admin2 != null) {
			throw new AdminAlreadyExsistsException("Insert Failed! Admin with Mobile Number "+admin.getMobileNumber()+" already exists!");
		}
		return adminRepository.save(admin);
	}

	//Service Method Implementation for updating an Admin
	@Override
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		Optional<Admin> admin1 = adminRepository.findById(admin.getAdminId());
		if(!admin1.isPresent()) {
			log.error("Update Failed! Admin with ID: "+admin.getAdminId()+" not found!");
			throw new AdminNotFoundException("Update Failed! Admin with ID: "+admin.getAdminId()+" not found!");
		}
		Admin admin2 = admin1.get();
		admin2.setAddress(admin.getAddress());
		admin2.setEmail(admin.getEmail());
		admin2.setMobileNumber(admin.getMobileNumber());
		admin2.setPassword(admin.getPassword());
		admin2.setUsername(admin.getUsername());
		adminRepository.save(admin2);
		return admin2;
	}

	//Service Method Implementation for deleting an Admin
	@Override
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		Optional<Admin> admin1 = adminRepository.findById(adminId);
		if(!admin1.isPresent()) {
			log.error("Delete Failed! Admin with ID: "+adminId+" not found!");
			throw new AdminNotFoundException("Delete Failed! Admin with ID: "+adminId+" not found!");
		}
		Admin admin2 = admin1.get();
		adminRepository.delete(admin2);
		return admin2;
	}

	//Service Method Implementation for Getting All Trips based on Customer Id
	@Override
	public List<TripBooking> getAllTrips(int customerId) {
		log.info("Service Triggered");
		// TODO Auto-generated method stub
		List<TripBooking> allTrips = adminRepository.getAllTrips(customerId);
		if(allTrips.isEmpty()) {
			log.error("No Trip Bookings Found!");
			throw new TripBookingNotFoundException("No Trip Bookings Found!");
		}
		return allTrips;
	}

	//Service Method Implementation for Getting All Trips Cab Wise
	@Override
	public List<TripBooking> getTripsCabwise() {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		List<TripBooking> trips = adminRepository.getTripsCabwise();
		if(trips.isEmpty()) {
			log.error("No Trip Bookings Found!");
			throw new TripBookingNotFoundException("No Trip Bookings Found!");
		}
		return trips;
	}

	//Service Method Implementation for Getting All Trips Customer Wise
	@Override
	public List<TripBooking> getTripsCustomerwise() {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		List<TripBooking> trips = adminRepository.getTripsCustomerwise();
		if(trips.isEmpty()) {
			log.error("No Trip Bookings Found!");
			throw new TripBookingNotFoundException("No Trip Bookings Found!");
		}
		return trips;
	}

	//Service Method Implementation for Getting All Trips Date Wise
	@Override
	public List<TripBooking> getTripsDatewise() {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		List<TripBooking> trips = adminRepository.getTripsDatewise();
		if(trips.isEmpty()) {
			log.error("No Trip Bookings Found!");
			throw new TripBookingNotFoundException("No Trip Bookings Found!");
		}
		return trips;
	}

	//Service Method Implementation for Getting All Trips for a particular customer Id & a particular time frame
	@Override
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		List<TripBooking> trips = adminRepository.getAllTripsForDays(customerId,fromDate,toDate);
		if(trips.isEmpty()) {
			log.error("No Trip Bookings Found!");
			throw new TripBookingNotFoundException("No Trip Bookings Found!");
		}
		return trips;
	}

}
