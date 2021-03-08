/**
 * Author: Arfath Pasha
 */
package com.cg.cba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cba.entities.Customer;
import com.cg.cba.exception.CustomerAlreadyExistsException;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public Customer insertCustomer(Customer customer) throws CustomerAlreadyExistsException {
		// TODO Auto-generated method stub
		Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
		if(customer1.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer with ID "+customer.getCustomerId()+" already exists!");
		}
		
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
		if (!customer1.isPresent()) {
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

	@Override
	public Customer deleteCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
		if (!customer1.isPresent()) {
			throw new CustomerNotFoundException(
					"Delete Failed! Customer with ID: " + customer.getCustomerId() + " not found!");
		}
		customerRepository.delete(customer);
		return customer;
	}

	@Override
	public List<Customer> viewCustomers() {
		return customerRepository.viewCustomers();
	}
	
	@Override
	public Customer viewCustomer(int customerId) {
		// TODO Auto-generated method stub
	
		return customerRepository.viewCustomer(customerId);
		
	}
	
	@Override
	public Customer validateCustomer(String username, String password) {
		// TODO Auto-generated method stub
		List<Customer> customerList = customerRepository.findAll();
		for(Customer c : customerList) {
			if(c.getUsername().equals(username)) {
				if(c.getPassword().equals(password)) {
					return c;
				}
			}
		}
		throw new CustomerNotFoundException("Invalid Credentials!");
	}

}
