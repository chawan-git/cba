/**
 * Author: Arfath Pasha
 */
package com.cg.cba.controller;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cba.entities.Customer;
import com.cg.cba.exception.CustomerAlreadyExistsException;
import com.cg.cba.exception.InvalidInputException;
import com.cg.cba.service.CustomerServiceImpl;
import com.cg.cba.service.ICustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/customer")
@Api(value = "Customer Controller", description = "REST API for Customer Entity")
//Controller class for RESTful web services.
public class CustomerController {
	// Loosely coupling the Customer Service Interface
	@Autowired
	private ICustomerService customerService;
	
	//Method for checking the input whether it is a valid input or not
	public void validateInput(Customer customer) {
		if(customer == null || customer.getUsername() == null || customer.getPassword() == null || customer.getMobileNumber() == null || customer.getEmail() == null || customer.getUsername().equals("")) {
			throw new InvalidInputException("customer Details can't be null");
		}
		if(!Pattern.compile(".{6}.*").matcher(customer.getPassword()).find()) {
			throw new InvalidInputException("Invalid Password format! Min 6 characters required");
		}
		if(!Pattern.compile("(0/91)?[6-9][0-9]{9}").matcher(customer.getMobileNumber()).find()) {
			throw new InvalidInputException("Enter a valid Mobile Number");
		}
		if(!Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-].[a-zA-Z0-9.-]+$").matcher(customer.getEmail()).find()) {
			throw new InvalidInputException("Enter a valid Email Address");
		}
		
	}

	//Creating a Logger Object to perform Log Operations
	private static final Logger log = LogManager.getLogger(CustomerServiceImpl.class);

	// End point for inserting customer
	@ApiOperation(value = "Used to insertCustomer and returns Customer details")
	@PostMapping(value = "insertCustomer")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistsException {
		log.info("Controller Triggered");
		validateInput(customer);
		Customer customer1 = customerService.insertCustomer(customer);
		return new ResponseEntity<Customer>(customer1,HttpStatus.CREATED);
	}

	// End point to fetch customer by customerId
	@ApiOperation(value = "Used to get customer details based on customer id")
	@GetMapping(value = "getCustomerById/{cid}")
	public ResponseEntity<Customer> getCustomerByID(@PathVariable int cid) {
		log.info("Controller Triggered");
		Customer customer = customerService.viewCustomer(cid);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// End point to fetch all customers
	@ApiOperation(value = "Used to getAllCustomers and returns list of customer details")
	@GetMapping(path = "getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		log.info("Controller Triggered");
		List<Customer> customers = customerService.viewCustomers();
		ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		return responseEntity;
	}

	// End point for deleting customer
	@ApiOperation(value = "Used to deleteCustomer and returns Customer details")
	@DeleteMapping(value = "deleteCustomer/{cid}")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable int cid) {
		log.info("Controller Triggered");
		Customer customer = customerService.deleteCustomer(cid);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// End point for updating customer
	@ApiOperation(value = "Used to updateCustomer and returns Customer details")
	@PutMapping(value = "updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		log.info("Controller Triggered");
		validateInput(customer);
		Customer customer1 = customerService.updateCustomer(customer);
		return new ResponseEntity<Customer>(customer1, HttpStatus.OK);

	}

	// End point for validating customer
	@ApiOperation(value = "Used to validateCustomer and returns Customer details")
	@PostMapping("validateCustomer")
	public Customer ValidateCustomer(@RequestBody Map<String, String> userMap) {
		log.info("Controller Triggered");
		if(userMap.get("username") == null || userMap.get("username").equals("") || userMap.get("password") == null || userMap.get("password").equals("")) {
			throw new InvalidInputException("Username or Password cannot be null!");
		}
		String username = userMap.get("username");
		String password = userMap.get("password");
		return customerService.validateCustomer(username, password);
	}
	
	@ApiOperation(value = "Used to get customer details based on MobileNumber")
	@GetMapping(value = "getCustomerByMobileNumber/{mobileNumber}")
	public ResponseEntity<Customer> getCustomerByMobileNumber(@PathVariable String mobileNumber) {
		log.info("Controller Triggered");
		Customer customer = customerService.getCustomerByMobileNumber(mobileNumber);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to get customer details based on Username")
	@GetMapping(value = "getCustomerByUsername/{username}")
	public ResponseEntity<Customer> getCustomerByUsername(@PathVariable String username) {
		log.info("Controller Triggered");
		Customer customer = customerService.getCustomerByUsername(username);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Used to get customer details based on Email")
	@GetMapping(value = "getCustomerByEmail/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
		log.info("Controller Triggered");
		Customer customer = customerService.getCustomerByEmail(email);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
}
