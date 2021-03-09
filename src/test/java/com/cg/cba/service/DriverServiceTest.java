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
public class DriverServiceTest 
{
	
	@MockBean
	private IDriverRepository driverRepository;
	
	@Autowired
	private IDriverService driverService; 
	
	@Test
	public void testInsertDriver() throws CabNotFoundException, DriverAlreadyExistsException  
	{
		Cab cab = new Cab();
		Driver driver = new Driver();
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
		when(driverRepository.save(driver)).thenReturn(driver);
		assertEquals(driver, driverService.insertDriver(driver));
	}
	
   @Test
	public void testUpdatedriver() throws CabNotFoundException, DriverNotFoundException
	{	
		Cab cab = new Cab();
		Driver driver = new Driver();
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
		when(driverRepository.save(driver)).thenReturn(driver);
		when(driverRepository.findById(driver.getDriverId())).thenReturn(Optional.of(driver));
		assertEquals(driver, driverService.updateDriver(driver));
	}

	@Test
	public void testDeleteDriver() throws CabNotFoundException, DriverNotFoundException 
	{	
		Cab cab = new Cab();
		Driver driver = new Driver();
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
		when(driverRepository.findById(driver.getDriverId())).thenReturn(Optional.of(driver));
		assertEquals(driver, driverService.deleteDriver(1));
	}

}