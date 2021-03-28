/**
 * Author: Arfath Pasha
 */
package com.cg.cba.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cba.entities.Customer;
import com.cg.cba.exception.AdminAlreadyExsistsException;
import com.cg.cba.exception.CustomerAlreadyExistsException;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.repository.ICustomerRepository;

//CustomerService class provides definition to the methods declared in ICustomerService interface.
@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerRepository customerRepository;

	//Creating a Logger Object to perform Log Operations
	private static final Logger log = LogManager.getLogger(CustomerServiceImpl.class);

	// insertCustomer method inserts customer details.
	@Override
	public Customer insertCustomer(Customer customer) throws CustomerAlreadyExistsException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
		if (customer1.isPresent()) {
			log.error("Insert Failed! Customer with ID: " + customer.getCustomerId() + " already exists!");
			throw new CustomerAlreadyExistsException(
					"Customer with ID " + customer.getCustomerId() + " already exists!");
		}

		Customer customer2 = customerRepository.findByEmail(customer.getEmail());
		if(customer2 != null) {
			throw new AdminAlreadyExsistsException("Insert Failed! customer with Email "+customer.getEmail()+" already exists!");
		}
		customer2 = customerRepository.findByUsername(customer.getUsername());
		if(customer2 != null) {
			throw new AdminAlreadyExsistsException("Insert Failed! customer with Username "+customer.getUsername()+" already exists!");
		}
		customer2 = customerRepository.findByMobileNumber(customer.getMobileNumber());
		if(customer2 != null) {
			throw new AdminAlreadyExsistsException("Insert Failed! customer with Mobile Number "+customer.getMobileNumber()+" already exists!");
		}
		return customerRepository.save(customer);
	}

	// updateCustomer method updates a customer details.
	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		log.info("Service Triggered");
		Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
		if (!customer1.isPresent()) {
			log.error("Update Failed! Customer with ID: " + customer.getCustomerId() + " not found!");
			throw new CustomerNotFoundException(
					"Update Failed! Customer with ID: " + customer.getCustomerId() + " not found!");
		}
		Customer customer2 = customer1.get();
		customer2.setAddress(customer.getAddress());
		customer2.setEmail(customer.getEmail());
		customer2.setMobileNumber(customer.getMobileNumber());
		customer2.setUsername(customer.getUsername());
		customer2.setPassword(customer.getPassword());
		customerRepository.save(customer2);
		return customer2;
	}

	// deleteCustomer method removes a customer details based on the customerId.
	@Override
	public Customer deleteCustomer(int customerId) throws CustomerNotFoundException {
		log.info("Service Triggered");
		Optional<Customer> customer1 = customerRepository.findById(customerId);
		if (!customer1.isPresent()) {
			log.error("Delete Failed! Customer with ID: " + customerId + " not found!");
			throw new CustomerNotFoundException("Delete Failed! Customer with ID: " + customerId + " not found!");
		}
		Customer customer2 = customer1.get();
		customerRepository.delete(customer2);
		return customer2;
	}

	// viewCustomers method list out all customers.
	@Override
	public List<Customer> viewCustomers() throws CustomerNotFoundException {
		log.info("Service Triggered");
		List<Customer> customers = customerRepository.viewCustomers();
		if (customers.isEmpty()) {
			log.error("No Customers Found!");
			throw new CustomerNotFoundException("Customer Not Found!");
		}
		return customers;

	}

	// viewCustomer method views a customer details based on the customerId.
	@Override
	public Customer viewCustomer(int customerId) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (!customer.isPresent()) {
			 log.error("No Customer Found!");
			throw new CustomerNotFoundException("Customer Not Found!");
		}
		
		return customer.get();

	}

	// validateCustomer method validates the username and password of customer.
	@Override
	public Customer validateCustomer(String username, String password) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		log.info("Service Triggered");
		List<Customer> customerList = customerRepository.findAll();
		for (Customer c : customerList) {
			if (c.getUsername().equals(username)) {
				if (c.getPassword().equals(password)) {
					return c;
				}
			}
		}
		log.error("No Customers Found!");
		throw new CustomerNotFoundException("Invalid Credentials!");
	}

	@Override
	public Customer getCustomerByMobileNumber(String mobileNumber) throws CustomerNotFoundException {
		// TODO Auto-generated method stub		log.info("Service Triggered");
		Customer customer = customerRepository.findByMobileNumber(mobileNumber);
		if (customer.equals(null)) {
			 log.error("No Customer Found!");
			throw new CustomerNotFoundException("Customer Not Found!");
		}
		
		return customer;
	}

	@Override
	public Customer getCustomerByUsername(String username) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findByUsername(username);
		if (customer.equals(null)) {
			 log.error("No Customer Found!");
			throw new CustomerNotFoundException("Customer Not Found!");
		}
		
		return customer;
	}

	@Override
	public Customer getCustomerByEmail(String email) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findByEmail(email);
		if (customer.equals(null)) {
			 log.error("No Customer Found!");
			throw new CustomerNotFoundException("Customer Not Found!");
		}
		
		return customer;
	}

}
