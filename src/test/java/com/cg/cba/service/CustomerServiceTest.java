package com.cg.cba.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.cba.entities.Customer;
import com.cg.cba.exception.CustomerAlreadyExistsException;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.repository.ICustomerRepository;



/**
 * @author Arfath Pasha
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest 
{
	
	@MockBean
	private ICustomerRepository customerRepository;
	
	@Autowired
	private ICustomerService customerService; 
	
	@Test
	public void testInsertCustomer() throws CustomerAlreadyExistsException 
	{
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUsername("c1");
		customer.setPassword("cp1");
		customer.setAddress("caddr");
		customer.setEmail("c@c.com");
		customer.setMobileNumber("123");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.insertCustomer(customer));
	}
	
   @Test
	public void testUpdateCustomer() throws CustomerNotFoundException
	{	
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUsername("c1");
		customer.setPassword("cp1");
		customer.setAddress("caddr");
		customer.setEmail("c@c.com");
		customer.setMobileNumber("123");
		when(customerRepository.save(customer)).thenReturn(customer);
		when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
		assertEquals(customer, customerService.updateCustomer(customer));
	}

	@Test
	public void testDeleteCustomer() throws CustomerNotFoundException 
	{	
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUsername("c1");
		customer.setPassword("cp1");
		customer.setAddress("caddr");
		customer.setEmail("c@c.com");
		customer.setMobileNumber("123");
		when(customerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(customer));
		assertEquals(customer, customerService.deleteCustomer(1));
	}

}