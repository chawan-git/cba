/**
 * Author: D Sri Madhu Priya
 */
package com.cg.cba.service;

import java.util.List;

import com.cg.cba.entities.Cab;
import com.cg.cba.exception.CabAlreadyExistsException;
import com.cg.cba.exception.CabNotFoundException;
//This interface contains all the methods are are to be performed on the database
public interface ICabService
{
	public Cab insertCab(Cab cab) throws CabAlreadyExistsException;
	public Cab updateCab(Cab cab) throws CabNotFoundException;
	public Cab deleteCab(int cabId) throws CabNotFoundException;
	public List<Cab> viewCabsOfType(String carType) throws CabNotFoundException;
	public int countCabsOfType(String carType) throws CabNotFoundException;
	
	public List<Cab> viewAllCabs() throws CabNotFoundException;
	public Cab getCabById(int cabId) throws CabNotFoundException; 
}
