/**
 * 
 */
package com.cg.cba.service;

import java.util.List;
import java.util.Optional;

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
@Service
public class CabServiceImpl implements ICabService
{
	@Autowired
	private ICabRepository cabRepository;
	
	@Override
	public Cab insertCab(Cab cab) throws CabAlreadyExistsException {
		Optional<Cab> cab1 = cabRepository.findById(cab.getCabId());
		if(cab1.isPresent()) {
			throw new CabAlreadyExistsException("Cab with ID "+cab.getCabId()+" already exists!");
		}
		Cab cab2 = cabRepository.save(cab);
		return cab2;
	}

	@Override
	public Cab updateCab(Cab cab) throws CabNotFoundException{
		Optional<Cab> cab1 = cabRepository.findById(cab.getCabId());
		if(!cab1.isPresent()) {
			throw new CabNotFoundException("Cab with ID "+cab.getCabId()+" not found!");
		}
		Cab cab2 = cab1.get();
		cab2.setCarType(cab.getCarType());
		cab2.setPerKmRate(cab.getPerKmRate());
		return cabRepository.save(cab2);
	}

	@Override
	public Cab deleteCab(int cabId) throws CabNotFoundException{
		Optional<Cab> cab1 = cabRepository.findById(cabId);
		if(!cab1.isPresent()) {
			throw new CabNotFoundException("Cab with ID "+cabId+" not found!");
		}
		Cab cab2 = cab1.get();
		cabRepository.delete(cab2);

		return cab2;
	}

	@Override
	public List<Cab> viewCabsOfType(String carType) throws CabNotFoundException{
		
		List<Cab> cabs = cabRepository.viewCabsOfType(carType);
		if(cabs.isEmpty())
			throw new CabNotFoundException("Cab Not Found!");
		return cabs;
	}

	@Override
	public int countCabsOfType(String carType) throws CabNotFoundException{
		int count = cabRepository.countCabsOfType(carType);
		if(count < 1) {
			throw new CabNotFoundException("Cab Not Found!");
		}
		return count;
	}

}
