
package com.cg.cba.repository;

/**
 * @author D Sri Madhu Priya
 *
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.cba.entities.Cab;

@Repository
public interface ICabRepository extends JpaRepository<Cab, Integer>
{

	@Query("SELECT cab FROM Cab cab WHERE carType = ?1")
	public List<Cab> viewCabsOfType(String carType);
	@Query("SELECT COUNT(cab) FROM Cab cab WHERE carType = ?1")
	public int countCabsOfType(String carType);
}

