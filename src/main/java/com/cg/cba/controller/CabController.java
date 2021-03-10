/*
 * Author D Sri Madhu Priya
 */
package com.cg.cba.controller;

import com.cg.cba.entities.Cab;
import com.cg.cba.exception.CabAlreadyExistsException;
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

/* This would signify that we are in cab controller right now */
@RequestMapping(path="/api/v1/cab")
/*
 * This annotation defines a class as a controller in spring boot. Rest
 * controller consists of @Controller and @ResponseBody annotation
 */
@Api(value = "Cab Controller", description = "REST API for Cab Entity")
@RestController
public class CabController {
	//Initialising the Logger
	private static final Logger log = LogManager.getLogger(CabServiceImpl.class);
	
    @Autowired
    ICabService cabService;
    //Start point for inserting the cab
    @ApiOperation(value = "Insert Cab")
    @PostMapping(path="insertCab")
    public ResponseEntity<Cab> insertCab(@RequestBody Cab cab) throws CabAlreadyExistsException {
    	log.info("Controller Triggered");
    	Cab cab1 = cabService.insertCab(cab);
        ResponseEntity<Cab> responseEntity = new ResponseEntity<Cab>(cab1,HttpStatus.CREATED);
        return responseEntity;
    }
    //start point for updating the cab
    @ApiOperation(value = "Update Cab")
    @PutMapping(path="updateCab")
    public ResponseEntity<Cab> updateCab(@RequestBody Cab cab) throws CabAlreadyExistsException {
    	log.info("Controller Triggered");
    	Cab cab1 = cabService.updateCab(cab);
        ResponseEntity<Cab> responseEntity = new ResponseEntity<Cab>(cab1,HttpStatus.OK);
        return responseEntity;
    }
    //start point for deleting the cabs
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
}