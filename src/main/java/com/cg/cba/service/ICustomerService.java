/**
 * Author: Arfath Pasha
 */
package com.cg.cba.service;

import java.util.List;

import com.cg.cba.entities.Customer;
import com.cg.cba.exception.CustomerAlreadyExistsException;
import com.cg.cba.exception.CustomerNotFoundException;
public interface ICustomerService {
	public Customer insertCustomer(Customer customer) throws CustomerAlreadyExistsException;
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	public Customer deleteCustomer(int customerId) throws CustomerNotFoundException;
	public List<Customer> viewCustomers() throws CustomerNotFoundException;
	public Customer viewCustomer(int customerId) throws CustomerNotFoundException;
	public Customer validateCustomer(String username, String password) throws CustomerNotFoundException;
}
