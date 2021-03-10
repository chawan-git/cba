/**
 * 
 */
package com.cg.cba.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cba.entities.Admin;
import com.cg.cba.entities.TripBooking;
import com.cg.cba.exception.AdminAlreadyExsistsException;
import com.cg.cba.exception.AdminNotFoundException;
import com.cg.cba.service.AdminServiceImpl;

/**
 * @author arc
 *
 */
@RestController
@RequestMapping(path = "api/v1/admin")
public class AdminController {
	
	@Autowired
	private AdminServiceImpl adminService;

	@PostMapping(path = "insertAdmin")
	public ResponseEntity<Admin> insertAdmin(@RequestBody Admin admin) throws AdminAlreadyExsistsException{
		Admin admin1 = adminService.insertAdmin(admin);
		
		ResponseEntity<Admin> responseEntity = new ResponseEntity<Admin>(admin1,HttpStatus.CREATED);
		return responseEntity;
		
	}
	
	@PutMapping(path = "updateAdmin")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) throws AdminNotFoundException{
		Admin admin1 = adminService.updateAdmin(admin);
		
		ResponseEntity<Admin> responseEntity = new ResponseEntity<Admin>(admin1,HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping(path = "deleteAdmin/{id}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable int id) throws AdminNotFoundException{
		Admin admin1 = adminService.deleteAdmin(id);
		ResponseEntity<Admin> responseEntity = new ResponseEntity<Admin>(admin1,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(path = "getAllTrips/{id}")
	public ResponseEntity<List<TripBooking>> getAllTrips(@PathVariable int id){
		List<TripBooking> trips = adminService.getAllTrips(id);
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(path = "getTripsCabwise")
	public ResponseEntity<List<TripBooking>> getTripsCabwise(){
		List<TripBooking> trips = adminService.getTripsCabwise();
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(path = "getTripsCustomerwise")
	public ResponseEntity<List<TripBooking>> getTripsCustomerwise(){
		List<TripBooking> trips = adminService.getTripsCustomerwise();
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(path = "getTripsDatewise")
	public ResponseEntity<List<TripBooking>> getTripsDatewise(){
		List<TripBooking> trips = adminService.getTripsDatewise();
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(path = "getAllTripsForDays")
	public ResponseEntity<List<TripBooking>> getAllTripsForDays(@RequestParam(name = "customerId") int customerId, @RequestParam(name = "fromDate") LocalDateTime fromDate, @RequestParam(name = "toDate") LocalDateTime toDate){
		List<TripBooking> trips = adminService.getAllTripsForDays(customerId,fromDate,toDate);
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
}
