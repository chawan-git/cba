package com.cg.cba.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Arfath Pasha
 *
 */
import com.cg.cba.entities.Customer;
import com.cg.cba.exception.CustomerAlreadyExistsException;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.repository.ICustomerRepository;

//Test class for testing service layer 
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	//Mocking the database for customerRepository using the Mockito Framework
	@MockBean
	private ICustomerRepository customerRepository;

	//Loosely coupling the Customer Service Interface
	@Autowired
	private ICustomerService customerService;

	Customer customer = null;
	
	@BeforeEach
	public void testBefore()
	{
		customer = new Customer();
		customer.setCustomerId(1);
		customer.setUsername("c1");
		customer.setPassword("cp1");
		customer.setAddress("caddr");
		customer.setEmail("c@c.com");
		customer.setMobileNumber("123");
	}

	// Test method to test functionality of insertCustomer Method.
	@Test
	public void testInsertCustomer() throws CustomerAlreadyExistsException {
		
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.insertCustomer(customer));
	}

	// Test method to test functionality of updateCustomer Method.
	@Test
	public void testUpdateCustomer() throws CustomerNotFoundException {
		
		when(customerRepository.save(customer)).thenReturn(customer);
		when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
		assertEquals(customer, customerService.updateCustomer(customer));
	}

	// Test method to test functionality of deleteCustomer Method.
	@Test
	public void testDeleteCustomer() throws CustomerNotFoundException {
		
		when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
		assertEquals(customer, customerService.deleteCustomer(1));
	}

	//Test method to test if CustomerNotFoundException is thrown.
	@Test
	public void testCustomerNotFoundException() {
			
		when(customerRepository.findById(customer.getCustomerId())).thenThrow(new CustomerNotFoundException());
		assertThrows(CustomerNotFoundException.class, ()-> customerService.updateCustomer(customer));
	}

	@AfterEach
	public void testAfterEach()
	{
		customer = null;
	}

}