/**
 * 
 */
package com.cg.cba.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

//Service Method that implements the LoginService Interface
@Service
public class LoginServiceImpl implements ILoginService{
	
	//Logger initialisation
	public static final Logger log = LogManager.getLogger(LoginServiceImpl.class);
	//Loose Coupling the repositories.
	@Autowired // inject the object dependency
	private IAdminRepository adminRepository;
	@Autowired
	private ICustomerRepository customerRepository;
	@Autowired
	private IDriverRepository driverRepository;
	
	
	//Method to validate the user and return the user type
	@Override
	public String validateUser(String username, String password) throws UserCredentialsInvalidException {
		// TODO Auto-generated method stub
		//customer class
		List<Customer> customerList = customerRepository.findAll();
		for(Customer c : customerList) {
			
			if(c.getUsername().equals(username)) {
				
				
				if(c.getPassword().equals(password)) {
					log.info("Customer Found");
					return "Customer";
					
				}
			}
		}
		
		List<Driver> driverList = driverRepository.findAll();
		for(Driver d : driverList) {
			if(d.getUsername().equals(username)) {
								
				if(d.getPassword().equals(password)) {
					log.info("Driver Found");
					return "Driver";
				}
			}
		}
		
		List<Admin> adminList = adminRepository.findAll();
		for(Admin a: adminList) {
			if(a.getUsername().equals(username)) {
				
				if(a.getPassword().equals(password)) {
					log.info("Admin Found");
					return "Admin";
				}
			}
		}
		log.error("Invalid Credentials");
		throw new UserCredentialsInvalidException("Invalid Credentials!");
		
	}
}
