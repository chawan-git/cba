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
	public Customer deleteCustomer(int customerId) throws CustomerNotFoundException {
		Optional<Customer> customer1 = customerRepository.findById(customerId);
		if (!customer1.isPresent()) {
			throw new CustomerNotFoundException(
					"Delete Failed! Customer with ID: " + customerId + " not found!");
		}
		customerRepository.delete(customer1.get());
		return customer1.get();
	}

	@Override
	public List<Customer> viewCustomers() throws CustomerNotFoundException{
		List<Customer> customers = customerRepository.viewCustomers();
		if(customers.isEmpty()) {
			throw new CustomerNotFoundException("Customer Not Found!");
		}
		return customers;
		
	}
	
	@Override
	public Customer viewCustomer(int customerId) throws CustomerNotFoundException{
		// TODO Auto-generated method stub
	
		Customer customer = customerRepository.viewCustomer(customerId);
		if(customer.getCustomerId() < 1)
			throw new CustomerNotFoundException("Customer Not Found!");
		return customer;
		
	}
	
	@Override
	public Customer validateCustomer(String username, String password) throws CustomerNotFoundException{
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
