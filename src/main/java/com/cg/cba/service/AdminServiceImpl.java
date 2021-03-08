/**
 * 
 */
package com.cg.cba.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cba.entities.Admin;
import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.AdminAlreadyExsistsException;
import com.cg.cba.exception.AdminNotFoundException;
import com.cg.cba.repository.IAdminRepository;

/**
 * @author arc
 *
 */
@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminRepository adminRepository;
	
	@Override
	public Admin insertAdmin(Admin admin) throws AdminAlreadyExsistsException {
		// TODO Auto-generated method stub
		Optional<Admin> admin1 = adminRepository.findById(admin.getAdminId());
		if(!admin1.isPresent()) {
			throw new AdminAlreadyExsistsException("Insert Failed! Admin with ID: "+admin.getAdminId()+" already exists!");
		}
		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
		// TODO Auto-generated method stub
		Optional<Admin> admin1 = adminRepository.findById(admin.getAdminId());
		if(!admin1.isPresent()) {
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

	@Override
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException {
		// TODO Auto-generated method stub
		Optional<Admin> admin1 = adminRepository.findById(adminId);
		if(!admin1.isPresent()) {
			throw new AdminNotFoundException("Delete Failed! Admin with ID: "+adminId+" not found!");
		}
		Admin admin2 = admin1.get();
		adminRepository.delete(admin2);
		return admin2;
	}

	@Override
	public List<TripBooking> getAllTrips(int customerId) {
		// TODO Auto-generated method stub
		return adminRepository.getAllTrips(customerId);
	}

	@Override
	public List<TripBooking> getTripsCabwise() {
		// TODO Auto-generated method stub
		return adminRepository.getTripsCabwise();
	}

	@Override
	public List<TripBooking> getTripsCustomerwise() {
		// TODO Auto-generated method stub
		return adminRepository.getTripsCustomerwise();
	}

	@Override
	public List<TripBooking> getTripsDatewise() {
		// TODO Auto-generated method stub
		return adminRepository.getTripsDatewise();
	}

	@Override
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		// TODO Auto-generated method stub
		return adminRepository.getAllTripsForDays(customerId, fromDate, toDate);
	}

}
