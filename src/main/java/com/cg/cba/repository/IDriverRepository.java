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


//IDriverRepository extends JpaRepository in order to get ready-made methods for performing CRUD operations.
@Repository
public interface IDriverRepository extends JpaRepository<Driver,Integer> {

	//This method will fetch Driver Details data based on the driverId from database.
	@Query("SELECT driver FROM Driver driver WHERE driverId = ?1")
	public Driver viewDriver(int driverId) throws DriverNotFoundException;

	//This method will fetch driver data based on the rating more than 4.5 from database.
	@Query("SELECT driver FROM Driver driver WHERE rating >= 4.5")
	public List<Driver> viewBestDrivers();
	
	//This method will fetch driver License Number from database.
	@Query("SELECT driver FROM Driver driver WHERE driver.licenseNo = ?1")
	public Driver findByLicense(String licenseNo);
	
	//This method will fetch driver data based on email from database.
	@Query("SELECT driver FROM Driver driver WHERE driver.email = ?1")
	public Driver findByEmail(String email);
	
	//This method will fetch driver user name from database.
	@Query("SELECT driver FROM Driver driver WHERE driver.username = ?1")
	public Driver findByUsername(String username);
	
	//This method will fetch driver mobile Number from database.
	@Query("SELECT driver FROM Driver driver WHERE driver.mobileNumber = ?1")
	public Driver findByMobileNumber(String mobileNumber);

}
