package com.cg.cba.controller;

import com.cg.cba.entities.Cab;
import com.cg.cba.exception.CabAlreadyExistsException;
import com.cg.cba.service.ICabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(path="api/v1/cab")
@RestController
public class CabController {

    @Autowired
    ICabService cabService;

    @PostMapping(path="insertCab")
    public ResponseEntity<Cab> insertCab(@RequestBody Cab cab) throws CabAlreadyExistsException {
        Cab cab1 = cabService.insertCab(cab);
        ResponseEntity<Cab> responseEntity = new ResponseEntity<Cab>(cab1,HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping(path="updateCab")
    public ResponseEntity<Cab> updateCab(@RequestBody Cab cab) throws CabAlreadyExistsException {
    	Cab cab1 = cabService.updateCab(cab);
        ResponseEntity<Cab> responseEntity = new ResponseEntity<Cab>(cab1,HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(path="deleteCab/{id}")
    public ResponseEntity<Cab> deleteCab(@PathVariable int id) {
    	Cab cab1 = cabService.deleteCab(id);
        ResponseEntity<Cab> responseEntity = new ResponseEntity<Cab>(cab1,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(path="cabsOfType/{cabType}")
    public ResponseEntity<List<Cab>> getCabsOfType(@PathVariable("cabType")String cabType) {
        List<Cab> cabs = cabService.viewCabsOfType(cabType);
        if(cabs.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(cabs));
    }

    @GetMapping("countOfCabsType/{cabType}")
    public int getCountCabsOfType(@PathVariable("cabType")String cabType) {
        int count = cabService.countCabsOfType(cabType);
        return count;
    }
}