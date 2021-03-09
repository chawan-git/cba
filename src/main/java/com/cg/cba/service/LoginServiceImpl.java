/**
 * 
 */
package com.cg.cba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cba.entities.Admin;
import com.cg.cba.entities.Customer;
import com.cg.cba.entities.Driver;
import com.cg.cba.exception.UserCredentialsInvalidException;
import com.cg.cba.repository.IAdminRepository;
import com.cg.cba.repository.ICustomerRepository;
import com.cg.cba.repository.IDriverRepository;

/**
 * @author Ankitha Suraksha
 *
 */
@Service
public class LoginServiceImpl implements ILoginService{
	@Autowired
	private IAdminRepository adminRepository;
	@Autowired
	private ICustomerRepository customerRepository;
	@Autowired
	private IDriverRepository driverRepository;
	
	@Override
	public String validateUser(String username, String password) throws UserCredentialsInvalidException {
		// TODO Auto-generated method stub
		
		List<Customer> customerList = customerRepository.findAll();
		for(Customer c : customerList) {
			if(c.getUsername().equals(username)) {
				if(c.getPassword().equals(password)) {
					return "Customer";
				}
			}
		}
		
		List<Driver> driverList = driverRepository.findAll();
		for(Driver d : driverList) {
			if(d.getUsername().equals(username)) {
				if(d.getPassword().equals(password)) {
					return "Driver";
				}
			}
		}
		
		List<Admin> adminList = adminRepository.findAll();
		for(Admin a: adminList) {
			if(a.getUsername().equals(username)) {
				if(a.getPassword().equals(password)) {
					return "Admin";
				}
			}
		}
		
		throw new UserCredentialsInvalidException("Invalid Credentials!");
		
	}
}
