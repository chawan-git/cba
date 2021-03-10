/**
 * 
 */
package com.cg.cba.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cba.entities.Cab;
import com.cg.cba.exception.CabAlreadyExistsException;
import com.cg.cba.exception.CabNotFoundException;
import com.cg.cba.repository.ICabRepository;

/**
 * @author D Sri Madhu Priya
 *
 */
/* This annotation marks the class as a service class */
//This service class will implement the ICabService Interface
@Service
public class CabServiceImpl implements ICabService
{
	/*
	 * ICabRepository is a interface which extends JPA repository. We are trying to
	 * call the reference of it. Using @Autowired annotation , the object of the
	 * repository will be created during run time dynamically
	 */
	@Autowired
	private ICabRepository cabRepository;
	/*
	 * this method will call cabRepository and will add the cab object using save
	 * method of the JPA repository
	 */
	//Initialising the Logger
	private static final Logger log = LogManager.getLogger(CabServiceImpl.class);
	
	@Override
	/*
	 * this method will call cabRepository and will add the cab object using save
	 * method of the JPA repository
	 */
	public Cab insertCab(Cab cab) throws CabAlreadyExistsException {
		log.info("Service Triggered");
		Optional<Cab> cab1 = cabRepository.findById(cab.getCabId());
		if(cab1.isPresent()) {
			log.error("Insertion failed! Cab with Id "+cab.getCabId()+" already exists!");
			throw new CabAlreadyExistsException("Cab with ID "+cab.getCabId()+" already exists!");
		}
		Cab cab2 = cabRepository.save(cab);
		return cab2;
	}

	@Override
	/*
	 * this method will call cabRepository and will update the cab object of the
	 * matching id. if however we don't have any object of matching id, we will
	 * throw a CabNotFound exception
	 */
	public Cab updateCab(Cab cab) throws CabNotFoundException{
		log.info("Service Triggered");
		Optional<Cab> cab1 = cabRepository.findById(cab.getCabId());
		if(!cab1.isPresent()) {
			log.error("Update failed! Cab with Id "+cab.getCabId()+" not found!");
			throw new CabNotFoundException("Cab with ID "+cab.getCabId()+" not found!");
		}
		Cab cab2 = cab1.get();
		cab2.setCarType(cab.getCarType());
		cab2.setPerKmRate(cab.getPerKmRate());
		return cabRepository.save(cab2);
	}

	@Override
	/*
	 * this method will delete the cab object with matching cab Id. if however
	 * we don't have any cab with cab id, we will throw CabNotFound exception
	 */
	public Cab deleteCab(int cabId) throws CabNotFoundException{
		log.info("Service Triggered");
		Optional<Cab> cab1 = cabRepository.findById(cabId);
		if(!cab1.isPresent()) {
			log.error("Could not delete! Cab with Id "+cabId+" not found!");
			throw new CabNotFoundException("Cab with ID "+cabId+" not found!");
		}
		Cab cab2 = cab1.get();
		cabRepository.delete(cab2);

		return cab2;
	}

	@Override
	/*
	 * this method will return list of cabs object matching carType. if no matching
	 * cab is found, we will throw CabNotFound exception
	 */
	public List<Cab> viewCabsOfType(String carType) throws CabNotFoundException{
		
		log.info("Service Triggered");
		List<Cab> cabs = cabRepository.viewCabsOfType(carType);
		if(cabs.isEmpty()) {
			log.error("Could not view! Cab type "+carType+" not found!");
			throw new CabNotFoundException("Cab Not Found!");
		}
		return cabs;
	}

	@Override
	/* this method will return a count of cabs with matching carType.If there is no matching
	 * cab is found, we will throw CabNotFoundException */
	public int countCabsOfType(String carType) throws CabNotFoundException{
		log.info("Service Triggered");
		int count = cabRepository.countCabsOfType(carType);
		if(count < 1) {
			log.error("Could not view! Cab type "+carType+" not found!");
			throw new CabNotFoundException("Cab Not Found!");
		}
		return count;
	}

}
