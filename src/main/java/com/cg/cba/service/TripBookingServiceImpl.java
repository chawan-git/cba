/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cba.entities.TripBooking;
import java.util.Optional;
import java.util.List;

import com.cg.cba.exception.CustomerNotFoundException;
import com.cg.cba.exception.TripAlreadyExistsException;
import com.cg.cba.exception.TripBookingNotFoundException;
import com.cg.cba.exception.ZeroDistanceException;
import com.cg.cba.repository.ITripBookingRepository;

@Service
public class TripBookingServiceImpl implements ITripBookingService {

	@Autowired
	private ITripBookingRepository tripBookingRepository;

	
	@Override
	public TripBooking insertTripBooking(TripBooking tripBooking) throws TripAlreadyExistsException {
				
		if(tripBooking.getCustomerId()==0)
			throw new CustomerNotFoundException("Cutomer Id cannot be empty or 0");
		
		if(tripBooking.getDistanceInKm()==0)
			throw new ZeroDistanceException("Distance cannot be empty or 0");
		
		Optional<TripBooking> tripBooking1 = tripBookingRepository.findById(tripBooking.getTripBookingId());
		
		if(tripBooking1.isPresent()) {
			throw new TripAlreadyExistsException("Trip with ID "+tripBooking.getTripBookingId()+" already exists");
		}
		TripBooking tb = tripBookingRepository.save(tripBooking);
		
		return tb;
		
	}

	
	@Override
	public TripBooking updateTripBooking(TripBooking tripBooking) {

		if(tripBooking.getTripBookingId()==0)
			throw new TripBookingNotFoundException("Trip Booking ID cannot be empty or 0");
		
		Optional<TripBooking> tripBooking1 = tripBookingRepository.findById(tripBooking.getTripBookingId());
		
		if(!tripBooking1.isPresent()) {
			throw new TripBookingNotFoundException("Trip with ID "+tripBooking.getTripBookingId()+" not found.");
		}

		TripBooking tripBooking2 = tripBooking1.get();
		// update section
		tripBooking2.setCustomerId(tripBooking.getCustomerId());
		tripBooking2.setDriver(tripBooking.getDriver());
		tripBooking2.setFromLocation(tripBooking.getFromLocation());
		tripBooking2.setToLocation(tripBooking.getToLocation());
		tripBooking2.setFromDateTime(tripBooking.getFromDateTime());
		tripBooking2.setToDateTime(tripBooking.getToDateTime());
		tripBooking2.setStatus(tripBooking.isStatus());
		tripBooking2.setDistanceInKm(tripBooking.getDistanceInKm());
		tripBooking2.setBill(tripBooking.getBill());
		
		tripBookingRepository.save(tripBooking2);

		return tripBooking2;
	}

	
	@Override
	public TripBooking deleteTripBooking(int tripBookingId) {
		
		if(tripBookingId==0)
			throw new TripBookingNotFoundException("Trip Booking ID cannot be empty or 0");
		
		Optional<TripBooking> tripBooking1 = tripBookingRepository.findById(tripBookingId);
		
		if(!tripBooking1.isPresent()) {
			throw new TripBookingNotFoundException("Trip with ID "+tripBookingId+" not found.");
		}

		TripBooking tripBooking2 = tripBooking1.get();
		tripBookingRepository.delete(tripBooking2);
		return tripBooking2;
	}

	
	@Override
	public List<TripBooking> viewAllTripsCustomer(int customerId) {
		
		return tripBookingRepository.viewAllTripsCustomer(customerId);
	}

	
	@Override
	public TripBooking calculateBill(int tripBookingId) {
		
		if(tripBookingId==0)
			throw new TripBookingNotFoundException("Trip Booking ID cannot be empty or 0");
		
		Optional<TripBooking> tripBooking1 = tripBookingRepository.findById(tripBookingId);
		
		if(!tripBooking1.isPresent()) {
			throw new TripBookingNotFoundException("Trip with ID "+tripBookingId+" not found.");
		}

		TripBooking tripBooking2 = tripBooking1.get();
		
		float perKmRate = tripBooking2.getDriver().getCab().getPerKmRate();
		float distance = tripBooking2.getDistanceInKm();				
		tripBooking2.setBill(distance*perKmRate);	
		tripBookingRepository.save(tripBooking2);	
		return tripBooking2;
		
	}
	
}
