/*
 * Author D Sri Madhu Priya
 */
package com.cg.cba.controller;

import com.cg.cba.entities.Cab;
import com.cg.cba.exception.CabAlreadyExistsException;
import com.cg.cba.exception.InvalidInputException;
import com.cg.cba.service.CabServiceImpl;
import com.cg.cba.service.ICabService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/* This would signify that we are in cab controller right now */
@RequestMapping(path="/api/v1/cab")
/*
 * This annotation defines a class as a controller in spring boot. Rest
 * controller consists of @Controller and @ResponseBody annotation
 */
@Api(value = "Cab Controller", description = "REST API for Cab Entity")
@CrossOrigin(origins = {"http://localhost:3000","https://cab-test.rao.life","https://cab.rao.life"})
@RestController
public class CabController {
	//Initialising the Logger
	private static final Logger log = LogManager.getLogger(CabServiceImpl.class);
	
    @Autowired
    private ICabService cabService;
    
    //Method for checking the input whether it is a valid input or not
  	public void validateInput(Cab cab) {
  		if(cab == null || cab.getCarType() == null || cab.getCarType().equals("") || cab.getPerKmRate() == 0.0 || cab.getPerKmRate() < 0) {
  			throw new InvalidInputException("Car details can't be null");
  		}
  		if(!Pattern.compile("[a-zA-Z]").matcher(cab.getCarType()).find()){
  			throw new InvalidInputException("Invalid Car Type format!");
  				}
  		if(!Pattern.compile("[0-9+_.-]+").matcher(Float.toString(cab.getPerKmRate())).find()) {
			throw new InvalidInputException("Enter a valid Rate for Cab");
		  }
  	}
    //End point for inserting the cab
    @ApiOperation(value = "Insert Cab")
    @PostMapping(path="insertCab")
    public ResponseEntity<Cab> insertCab(@RequestBody Cab cab) throws CabAlreadyExistsException {
    	log.info("Controller Triggered");
    	validateInput(cab);
    	Cab cab1 = cabService.insertCab(cab);
        ResponseEntity<Cab> responseEntity = new ResponseEntity<Cab>(cab1,HttpStatus.CREATED);
        return responseEntity;
    }
    //End point for updating the cab
    @ApiOperation(value = "Update Cab")
    @PutMapping(path="updateCab")
    public ResponseEntity<Cab> updateCab(@RequestBody Cab cab) throws CabAlreadyExistsException {
    	log.info("Controller Triggered");
    	validateInput(cab);
    	Cab cab1 = cabService.updateCab(cab);
        ResponseEntity<Cab> responseEntity = new ResponseEntity<Cab>(cab1,HttpStatus.OK);
        return responseEntity;
    }
    //end point for deleting the cabs
    @ApiOperation(value = "Delete Cab")
    @DeleteMapping(path="deleteCab/{id}")
    public ResponseEntity<Cab> deleteCab(@PathVariable int id) {
    	log.info("Controller Triggered");
    	Cab cab1 = cabService.deleteCab(id);
        ResponseEntity<Cab> responseEntity = new ResponseEntity<Cab>(cab1,HttpStatus.OK);
        return responseEntity;
    }
    //End point for getting the type of cabs
    @ApiOperation(value = "Cabs of a particular type")
    @GetMapping(path="cabsOfType/{cabType}")
    public ResponseEntity<List<Cab>> getCabsOfType(@PathVariable("cabType")String cabType) {
    	log.info("Controller Triggered");
    	List<Cab> cabs = cabService.viewCabsOfType(cabType);
        if(cabs.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(cabs));
    }
    //End point for getting the number of cabs of a particular type
    @ApiOperation(value = "Count of Cabs of a particular Type")
    @GetMapping(path="countOfCabsType/{cabType}")
    public int getCountCabsOfType(@PathVariable("cabType")String cabType) {
    	log.info("Controller Triggered");
    	int count = cabService.countCabsOfType(cabType);
        return count;
    }
    
    //End point for getting all the type of cabs
    @ApiOperation(value = "Cabs of all types")
    @GetMapping(path="viewAllCabs")
    public ResponseEntity<List<Cab>> viewAllCabs() {
    	log.info("Controller Triggered");
    	List<Cab> cabs = cabService.viewAllCabs();
        if(cabs.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(cabs));
    }
    
    //End point for getting all the type of cabs
    @ApiOperation(value = "Get Cab By Id")
    @GetMapping(path="getCabById/{cabId}")
    public ResponseEntity<Cab> getCabById(@PathVariable int cabId) {
    	log.info("Controller Triggered");
    	Cab cab = cabService.getCabById(cabId);
        ResponseEntity<Cab> responseEntity = new ResponseEntity<Cab>(cab,HttpStatus.OK);
        return responseEntity;
    }
}