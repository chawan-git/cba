/**
 * Author: Arfath Pasha
 */
package com.cg.cba.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cba.entities.Customer;
import com.cg.cba.exception.CustomerAlreadyExistsException;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.service.ICustomerService;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/v1/customer")

public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	@PostMapping(value = "saveCustomer")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistsException {
		Customer customer1 = customerService.insertCustomer(customer);
		return new ResponseEntity<String>("Customer With ID :" + customer1.getCustomerId() + " Created Successfully",
				HttpStatus.CREATED);
	}

	@GetMapping(value = "getOneCustomer/{cid}")
	public ResponseEntity<Customer> getCustomerByID(@PathVariable Integer cid) {
		Customer customer = customerService.viewCustomer(cid);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping(path = "getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = customerService.viewCustomers();
		ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping(value = "deleteCustomer/{cid}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Integer cid) {
		Customer customer = customerService.viewCustomer(cid);
		customerService.deleteCustomer(cid);
		return new ResponseEntity<String>("Customer With ID :" + cid + " Deleted Successfully", HttpStatus.OK);
	}

	@PutMapping(value = "updateCustomer/{cid}")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer cust1, @PathVariable Integer cid) {
		Customer customer1 = customerService.viewCustomer(cid);
		customer1.setUsername(cust1.getUsername());
		customer1.setPassword(cust1.getPassword());
		customer1.setEmail(cust1.getEmail());
		customer1.setMobileNumber(cust1.getMobileNumber());
		customer1.setAddress(cust1.getAddress());
		customerService.updateCustomer(customer1);
		System.out.println(cust1.getUsername());
		return new ResponseEntity<String>("Customer With ID :" + cid + " Updated Successfully", HttpStatus.OK);

	}

	@GetMapping("validateCustomer")
    public Customer ValidateCustomer(@RequestBody Map<String, String> userMap){
		String username = userMap.get("username");
		String password = userMap.get("password");
        return customerService.validateCustomer(username,password);
    }
}
