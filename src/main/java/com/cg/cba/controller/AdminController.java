/**
 * 
 */
package com.cg.cba.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.cg.cba.exception.InvalidInputException;
import com.cg.cba.service.AdminServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author arc
 *
 */

//Controller EndPoint for Admin
@Api(value = "Admin Controller", description = "REST API for Admin Entity")
@RestController
@RequestMapping(path = "/api/v1/admin")
public class AdminController {
	
	//Initialising the Logger
	private static final Logger log = LogManager.getLogger(AdminController.class);
	
	//Loose coupling the Admin Service Implementation Class
	@Autowired
	private AdminServiceImpl adminService;

	//Method for checking the input whether it is a valid input or not
	public void validateInput(Admin admin) {
		if(admin == null || admin.getUsername() == null || admin.getPassword() == null || admin.getMobileNumber() == null || admin.getEmail() == null || admin.getUsername().equals("")) {
			throw new InvalidInputException("Admin Details can't be null");
		}
		if(!Pattern.compile(".{6}.*").matcher(admin.getPassword()).find()) {
			throw new InvalidInputException("Invalid Password format! Min 6 characters required");
		}
		if(!Pattern.compile("(0/91)?[6-9][0-9]{9}").matcher(admin.getMobileNumber()).find()) {
			throw new InvalidInputException("Enter a valid Mobile Number");
		}
		if(!Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-].[a-zA-Z0-9.-]+$").matcher(admin.getEmail()).find()) {
			throw new InvalidInputException("Enter a valid Email Address");
		}
	}
	
	
	//End Point for Inserting an admin
	@ApiOperation(value = "Insert Admin")
	@PostMapping(path = "insertAdmin")
	public ResponseEntity<Admin> insertAdmin(@RequestBody Admin admin) throws AdminAlreadyExsistsException{
		log.info("Controller Triggered");
		validateInput(admin);
		Admin admin1 = adminService.insertAdmin(admin);
		
		ResponseEntity<Admin> responseEntity = new ResponseEntity<Admin>(admin1,HttpStatus.CREATED);
		return responseEntity;
		
	}
	
	//End Point for Updating an admin
	@ApiOperation(value = "Update Admin")
	@PutMapping(path = "updateAdmin")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) throws AdminNotFoundException{
		log.info("Controller Triggered");
		validateInput(admin);
		Admin admin1 = adminService.updateAdmin(admin);
		
		ResponseEntity<Admin> responseEntity = new ResponseEntity<Admin>(admin1,HttpStatus.OK);
		return responseEntity;
	}
	
	//End Point for Deleting an admin
	@ApiOperation(value = "Delete Admin")
	@DeleteMapping(path = "deleteAdmin/{id}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable int id) throws AdminNotFoundException{
		log.info("Controller Triggered");
		Admin admin1 = adminService.deleteAdmin(id);
		ResponseEntity<Admin> responseEntity = new ResponseEntity<Admin>(admin1,HttpStatus.OK);
		return responseEntity;
	}
	
	//End Point for Getting All Trips for a particular customer Id
	@ApiOperation(value = "Get All Trips for a particular customer based on customer Id")
	@GetMapping(path = "getAllTrips/{id}")
	public ResponseEntity<List<TripBooking>> getAllTrips(@PathVariable int id){
		log.info("Controller Triggered");
		List<TripBooking> trips = adminService.getAllTrips(id);
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	//End Point for Getting All Trips Cab Wise
	@ApiOperation(value = "Get All Trips Cabwise")
	@GetMapping(path = "getTripsCabwise")
	public ResponseEntity<List<TripBooking>> getTripsCabwise(){
		log.info("Controller Triggered");
		List<TripBooking> trips = adminService.getTripsCabwise();
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	//End Point for Getting All Trips Customer Wise
	@ApiOperation(value = "Get All Trips Customerwise")
	@GetMapping(path = "getTripsCustomerwise")
	public ResponseEntity<List<TripBooking>> getTripsCustomerwise(){
		log.info("Controller Triggered");
		List<TripBooking> trips = adminService.getTripsCustomerwise();
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	//End Point for Getting All Trips Date Wise
	@ApiOperation(value = "Get All Trips Datewise")
	@GetMapping(path = "getTripsDatewise")
	public ResponseEntity<List<TripBooking>> getTripsDatewise(){
		log.info("Controller Triggered");
		List<TripBooking> trips = adminService.getTripsDatewise();
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	//End Point for Getting All Trips for a particular customer id & particular time frame
	@ApiOperation(value = "Get All Trips for a particular customer & particular time frame based on customer Id")
	@GetMapping(path = "getAllTripsForDays")
	public ResponseEntity<List<TripBooking>> getAllTripsForDays(@RequestParam(name = "customerId") int customerId, @RequestParam(name = "fromDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime, @RequestParam(name = "toDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime){
		log.info("Controller Triggered");
		List<TripBooking> trips = adminService.getAllTripsForDays(customerId,fromDateTime,toDateTime);
		ResponseEntity<List<TripBooking>> responseEntity = new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
		return responseEntity;
	}
	
	
	//End Point for Getting All Admins
	@ApiOperation(value = "Get All Admins")
	@GetMapping(path = "getAllAdmins")
	public ResponseEntity<List<Admin>> getAllAdmins(){
		log.info("Controller Triggered");
		List<Admin> admins = adminService.getAllAdmins();
		ResponseEntity<List<Admin>> responseEntity = new ResponseEntity<List<Admin>>(admins,HttpStatus.OK);
		return responseEntity;
	}
	
	//End Point for Getting admin based on mobileNumber
	@ApiOperation(value = "Get Admin based on mobileNumber")
	@GetMapping(path = "getAdminByMobileNumber/{mobileNumber}")
	public ResponseEntity<Admin> getAdminByMobileNumber(@PathVariable String mobileNumber){
		log.info("Controller Triggered");
		Admin admin = adminService.getAdminByMobileNumber(mobileNumber);
		ResponseEntity<Admin> responseEntity = new ResponseEntity<Admin>(admin,HttpStatus.OK);
		return responseEntity;
	}
	
	//End Point for Getting admin based on username
	@ApiOperation(value = "Get Admin based on username")
	@GetMapping(path = "getAdminByUsername/{username}")
	public ResponseEntity<Admin> getAdminByUsername(@PathVariable String username){
		log.info("Controller Triggered");
		Admin admin = adminService.getAdminByUsername(username);
		ResponseEntity<Admin> responseEntity = new ResponseEntity<Admin>(admin,HttpStatus.OK);
		return responseEntity;
	}
	
	//End Point for Getting admin based on email
	@ApiOperation(value = "Get Admin based on email")
	@GetMapping(path = "getAdminByEmail/{email}")
	public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email){
		log.info("Controller Triggered");
		Admin admin = adminService.getAdminByEmail(email);
		ResponseEntity<Admin> responseEntity = new ResponseEntity<Admin>(admin,HttpStatus.OK);
		return responseEntity;
	}
}
