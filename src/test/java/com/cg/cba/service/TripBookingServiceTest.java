package com.cg.cba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.cba.entities.Cab;
import com.cg.cba.entities.Customer;
import com.cg.cba.entities.Driver;
import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.exception.TripAlreadyExistsException;
import com.cg.cba.exception.TripBookingNotFoundException;
import com.cg.cba.repository.ITripBookingRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TripBookingServiceTest {

	
	@MockBean
	private ITripBookingRepository tripBookingRepository;
	@Autowired
	private ITripBookingService tripBookingService;
	
	@Test
	public void testInsertTripBooking() throws CustomerNotFoundException, TripAlreadyExistsException, DriverNotFoundException {
		Customer customer = new Customer();
		Cab cab = new Cab();
		Driver driver = new Driver();
		TripBooking tripBooking = new TripBooking();
		
		customer.setCustomerId(1);
		customer.setUsername("c1");
		customer.setPassword("cp1");
		customer.setAddress("caddr");
		customer.setEmail("c@c.com");
		customer.setMobileNumber("123");
		
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
		
		tripBooking.setTripBookingId(1);
		tripBooking.setCustomer(customer);
		tripBooking.setDriver(driver);
		tripBooking.setFromLocation("Bengaluru");
		tripBooking.setToLocation("Mysore");
		tripBooking.setFromDateTime(LocalDateTime.of(LocalDate.of(2021, 03, 9), LocalTime.of(07, 00, 00)));
		tripBooking.setToDateTime(LocalDateTime.of(LocalDate.of(2021, 03, 9), LocalTime.of(21, 0, 0)));
		tripBooking.setBill(100.00f);
		tripBooking.setStatus(true);
		tripBooking.setDistanceInKm(100.00f);
		
		when(tripBookingRepository.save(tripBooking)).thenReturn(tripBooking);
		assertEquals(tripBooking,tripBookingService.insertTripBooking(tripBooking));
	}
	
	@Test
	public void testUpdateTripBooking() throws CustomerNotFoundException, TripAlreadyExistsException, DriverNotFoundException {
		Customer customer = new Customer();
		Cab cab = new Cab();
		Driver driver = new Driver();
		TripBooking tripBooking = new TripBooking();
		
		customer.setCustomerId(1);
		customer.setUsername("c1");
		customer.setPassword("cp1");
		customer.setAddress("caddr");
		customer.setEmail("c@c.com");
		customer.setMobileNumber("123");
		
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
		
		tripBooking.setTripBookingId(1);
		tripBooking.setCustomer(customer);
		tripBooking.setDriver(driver);
		tripBooking.setFromLocation("Bengaluru");
		tripBooking.setToLocation("Mysore");
		tripBooking.setFromDateTime(LocalDateTime.of(LocalDate.of(2021, 03, 9), LocalTime.of(07, 00, 00)));
		tripBooking.setToDateTime(LocalDateTime.of(LocalDate.of(2021, 03, 9), LocalTime.of(21, 0, 0)));
		tripBooking.setBill(100.00f);
		tripBooking.setStatus(true);
		tripBooking.setDistanceInKm(100.00f);
		
		when(tripBookingRepository.findById(tripBooking.getTripBookingId())).thenReturn(Optional.of(tripBooking));
		when(tripBookingRepository.save(tripBooking)).thenReturn(tripBooking);
		
		assertEquals(tripBooking,tripBookingService.updateTripBooking(tripBooking));
	}
	
	@Test
	public void testDeleteTripBooking() throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {
		Customer customer = new Customer();
		Cab cab = new Cab();
		Driver driver = new Driver();
		TripBooking tripBooking = new TripBooking();
		
		customer.setCustomerId(1);
		customer.setUsername("c1");
		customer.setPassword("cp1");
		customer.setAddress("caddr");
		customer.setEmail("c@c.com");
		customer.setMobileNumber("123");
		
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
		
		tripBooking.setTripBookingId(1);
		tripBooking.setCustomer(customer);
		tripBooking.setDriver(driver);
		tripBooking.setFromLocation("Bengaluru");
		tripBooking.setToLocation("Mysore");
		tripBooking.setFromDateTime(LocalDateTime.of(LocalDate.of(2021, 03, 9), LocalTime.of(07, 00, 00)));
		tripBooking.setToDateTime(LocalDateTime.of(LocalDate.of(2021, 03, 9), LocalTime.of(21, 0, 0)));
		tripBooking.setBill(100.00f);
		tripBooking.setStatus(true);
		tripBooking.setDistanceInKm(100.00f);
		
		when(tripBookingRepository.findById(tripBooking.getTripBookingId())).thenReturn(Optional.of(tripBooking));
		when(tripBookingRepository.save(tripBooking)).thenReturn(tripBooking);
		assertEquals(tripBooking,tripBookingService.deleteTripBooking(tripBooking.getTripBookingId()));
	}
}
