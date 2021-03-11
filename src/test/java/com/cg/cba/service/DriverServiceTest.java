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

import com.cg.cba.entities.Cab;
import com.cg.cba.entities.Driver;
import com.cg.cba.exception.CabNotFoundException;
import com.cg.cba.exception.DriverAlreadyExistsException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.repository.IDriverRepository;

/**
 * @author Bharat Singh
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DriverServiceTest {

	@MockBean
	private IDriverRepository driverRepository;

	@Autowired
	private IDriverService driverService;

	Cab cab = null;
	Driver driver = null;

	@BeforeEach
	public void testBeforeEach()
	{
		cab = new Cab();
		driver = new Driver();
		cab.setCabId(1);
		cab.setCarType("SUV");
		cab.setPerKmRate(11.5f);

		driver.setDriverId(1);
		driver.setUsername("d1");
		driver.setPassword("dp1");
		driver.setAddress("daddr");
		driver.setEmail("d@d.com");
		driver.setMobileNumber("2345");
		driver.setLicenseNo("L123");
		driver.setRating(4.5f);
		driver.setCab(cab);
	}

	// Test method to test functionality of insertDriver Method.
	@Test
	public void testInsertDriver() throws CabNotFoundException, DriverAlreadyExistsException {
		
		when(driverRepository.save(driver)).thenReturn(driver);
		assertEquals(driver, driverService.insertDriver1(driver));
	}

	// Test method to test functionality of updateDriver Method.
	@Test
	public void testUpdatedriver() throws CabNotFoundException, DriverNotFoundException {
		when(driverRepository.save(driver)).thenReturn(driver);
		when(driverRepository.findById(driver.getDriverId())).thenReturn(Optional.of(driver));
		assertEquals(driver, driverService.updateDriver1(driver));
	}

	// Test method to test functionality of deleteDriver Method.
	@Test
	public void testDeleteDriver() throws CabNotFoundException, DriverNotFoundException {

		when(driverRepository.findById(driver.getDriverId())).thenReturn(Optional.of(driver));
		assertEquals(driver, driverService.deleteDriver(1));
	}

	// Test method to test if DriverNotFoundException is thrown when searching/updating Driver Details
	@Test
	public void testDriverNotFoundException() {
		
		when(driverRepository.findById(driver.getDriverId())).thenThrow(new DriverNotFoundException());
		assertThrows(DriverNotFoundException.class, ()->driverService.updateDriver(driver));

	}

	@AfterEach
	public void afterTest()
	{
		 cab = null;
		 driver = null;
	}

}