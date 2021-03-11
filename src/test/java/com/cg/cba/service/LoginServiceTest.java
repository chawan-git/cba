/**
Ankiitha Suraksha
 * 
 */
package com.cg.cba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
	
	Admin admin = null;
	Cab cab = null ;
	Driver driver = null;
	Customer customer = null;
	
	
	@BeforeEach	
	public void beforeTest() {
		
		admin = new Admin();
		cab = new Cab();
		driver = new Driver();
		customer = new Customer();
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

	}
	
	@AfterEach
	public void afterTest() {
		cab = null;
		driver = null;
		customer = null;
		admin = null;
	}
	
	@Test
	public void testLogIn() throws UserCredentialsInvalidException {
				
		List<Admin> adminList = new ArrayList<Admin>();
		List<Driver> driverList = new ArrayList<Driver>();
		List<Customer> customerList = new ArrayList<Customer>();
		adminList.add(admin);
		driverList.add(driver);
		customerList.add(customer);
		
		when(adminRepository.findAll()).thenReturn(adminList);
		when(driverRepository.findAll()).thenReturn(driverList);
		when(customerRepository.findAll()).thenReturn(customerList);
		
		
		assertEquals("Admin", loginService.signIn("a1", "pa1"));
		assertEquals("Driver", loginService.signIn("d1", "dp1"));
		assertEquals("Customer", loginService.signIn("c1", "cp1"));
		
	}

	@Test
	public void testLogOut() throws UserCredentialsInvalidException {
				
		List<Admin> adminList = new ArrayList<Admin>();
		List<Driver> driverList = new ArrayList<Driver>();
		List<Customer> customerList = new ArrayList<Customer>();
		adminList.add(admin);
		driverList.add(driver);
		customerList.add(customer);
		
		when(adminRepository.findAll()).thenReturn(adminList);
		when(driverRepository.findAll()).thenReturn(driverList);
		when(customerRepository.findAll()).thenReturn(customerList);
				
		assertEquals("Admin", loginService.signOut("a1", "pa1"));
		assertEquals("Driver", loginService.signOut("d1", "dp1"));
		assertEquals("Customer", loginService.signOut("c1", "cp1"));
		
	}
	
	@Test
	public void testUserCredentialsInvalidException() {
		
		List<Admin> adminList = new ArrayList<Admin>();
		List<Driver> driverList = new ArrayList<Driver>();
		List<Customer> customerList = new ArrayList<Customer>();
		adminList.add(admin);
		driverList.add(driver);
		customerList.add(customer);
		
		when(adminRepository.findAll()).thenThrow(UserCredentialsInvalidException.class);
		when(driverRepository.findAll()).thenThrow(UserCredentialsInvalidException.class);
		when(customerRepository.findAll()).thenThrow(UserCredentialsInvalidException.class);
				
		assertThrows(UserCredentialsInvalidException.class, () ->loginService.signOut("a1", "pa1"));
		assertThrows(UserCredentialsInvalidException.class, () ->loginService.signOut("d1", "dp1"));
		assertThrows(UserCredentialsInvalidException.class, () ->loginService.signOut("c1", "cp1"));
		
	}
	
}
