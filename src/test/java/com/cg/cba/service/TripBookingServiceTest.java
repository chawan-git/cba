package com.cg.cba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import com.cg.cba.entities.Customer;
import com.cg.cba.entities.Driver;
import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.exception.DriverNotFoundException;
import com.cg.cba.exception.TripAlreadyExistsException;
import com.cg.cba.exception.TripBookingNotFoundException;
import com.cg.cba.repository.ITripBookingRepository;

//Implementing a class to test service methods using JUnit5
@SpringBootTest
@RunWith(SpringRunner.class)
public class TripBookingServiceTest {

	// creating a temporary database test our methods
	@MockBean
	private ITripBookingRepository tripBookingRepository;

	// Performing ITripBookingRepository injection to a variable
	@Autowired
	private ITripBookingService tripBookingService;

	Customer customer = null;
	Cab cab = null;
	Driver driver = null;
	TripBooking tripBooking = null;

	@BeforeEach
	public void beforeEach() {
		// creating required objects

		customer = new Customer();
		cab = new Cab();
		driver = new Driver();
		tripBooking = new TripBooking();

		// setting the parameters
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
	}

	// test method to check inserting and returning of TripBooking Object
	@Test
	public void testInsertTripBooking() throws CustomerNotFoundException, TripAlreadyExistsException, DriverNotFoundException {

		when(tripBookingRepository.save(tripBooking)).thenReturn(tripBooking);
		assertEquals(tripBooking, tripBookingService.insertTripBooking1(tripBooking));
	}

	// test method to check inserting and returning of TripBooking Object
	@Test
	public void testUpdateTripBooking()	throws CustomerNotFoundException, TripAlreadyExistsException, DriverNotFoundException {

		when(tripBookingRepository.save(tripBooking)).thenReturn(tripBooking);
		when(tripBookingRepository.findById(tripBooking.getTripBookingId())).thenReturn(Optional.of(tripBooking));
		assertEquals(tripBooking, tripBookingService.updateTripBooking1(tripBooking));
	}

	// test method to check delete and returning of TripBooking Object
	@Test
	public void testDeleteTripBooking()	throws TripBookingNotFoundException, CustomerNotFoundException, DriverNotFoundException {

		when(tripBookingRepository.findById(tripBooking.getTripBookingId())).thenReturn(Optional.of(tripBooking));
		when(tripBookingRepository.save(tripBooking)).thenReturn(tripBooking);
		assertEquals(tripBooking, tripBookingService.deleteTripBooking(tripBooking.getTripBookingId()));
	}

	// Test method to test if TripBookingNotFoundException is thrown.
	@Test
	public void testTripBookingNotFoundException() {

		when(tripBookingRepository.findById(tripBooking.getTripBookingId())).thenThrow(new TripBookingNotFoundException());
		assertThrows(TripBookingNotFoundException.class,() -> tripBookingService.deleteTripBooking(tripBooking.getTripBookingId()));
	}
	
	@AfterEach
	public void testAfterEach()
	{
		 customer = null;
		 cab = null;
		 driver = null;
		 tripBooking = null;

	}

}
