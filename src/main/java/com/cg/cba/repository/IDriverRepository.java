/**
 * Author: Bharat Singh
 */

package com.cg.cba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.cba.entities.Driver;
import com.cg.cba.exception.DriverNotFoundException;

@Repository
public interface IDriverRepository extends JpaRepository<Driver,Integer> {

	@Query("SELECT driver FROM Driver driver WHERE driverId = ?1")
	public Driver viewDriver(int driverId) throws DriverNotFoundException;

	@Query("SELECT driver FROM Driver driver WHERE rating >= 4.5")
	public List<Driver> viewBestDrivers();

}
