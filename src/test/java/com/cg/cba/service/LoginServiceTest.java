/**
Ankiitha Suraksha
 * 
 */
package com.cg.cba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.cba.entities.Admin;
import com.cg.cba.entities.Cab;
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
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceTest {
	@Autowired
	private ILoginService loginService;
	@MockBean
	private IAdminRepository adminRepository;
	@MockBean
	private IDriverRepository driverRepository;
	@MockBean
	private ICustomerRepository customerRepository;
	@Test
	public void testValidateUser() throws UserCredentialsInvalidException {
		Admin admin = new Admin();
		Cab cab = new Cab();
		Driver driver = new Driver();
		Customer customer = new Customer();
		//admin class testing
		admin.setAdminId(1);
		admin.setUsername("a1");
		admin.setPassword("pa1");
		admin.setAddress("Addr of admin");
		admin.setEmail("admin@cg.com");
		admin.setMobileNumber("90350");
		
		// cab class testing
		cab.setCabId(1);
		cab.setCarType("SUV");
		cab.setPerKmRate(11.5f);
		
		// driver class testing
		driver.setDriverId(1);
		driver.setUsername("d1");
		driver.setPassword("dp1");
		driver.setAddress("daddr");
		driver.setEmail("d@d.com");
		driver.setMobileNumber("2345");
		driver.setLicenseNo("L123");
		driver.setRating(4.5f);
		driver.setCab(cab);
		
		//customer class testing
		customer.setCustomerId(1);
		customer.setUsername("c1");
		customer.setPassword("cp1");
		customer.setAddress("caddr");
		customer.setEmail("c@c.com");
		customer.setMobileNumber("123");
		
		List<Admin> adminList = new ArrayList<Admin>();
		List<Driver> driverList = new ArrayList<Driver>();
		List<Customer> customerList = new ArrayList<Customer>();
		adminList.add(admin);
		driverList.add(driver);
		customerList.add(customer);
		
		when(adminRepository.findAll()).thenReturn(adminList);
		when(driverRepository.findAll()).thenReturn(driverList);
		when(customerRepository.findAll()).thenReturn(customerList);
		
		
		assertEquals("Admin", loginService.validateUser("a1", "pa1"));
		assertEquals("Driver", loginService.validateUser("d1", "dp1"));
		assertEquals("Customer", loginService.validateUser("c1", "cp1"));
		
	}

	
	
}
