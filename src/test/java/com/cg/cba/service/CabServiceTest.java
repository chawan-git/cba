package com.cg.cba.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.cba.entities.Cab;
import com.cg.cba.exception.CabAlreadyExistsException;
import com.cg.cba.exception.CabNotFoundException;
import com.cg.cba.repository.ICabRepository;



/**
 * @author D Sri Madhu Priya
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CabServiceTest 
{
	
	@MockBean
	private ICabRepository cabRepository;
	
	@Autowired
	private ICabService cabService;
	
	@Test
	public void testInsertCab() throws CabAlreadyExistsException
	{
		Cab cab1 = new Cab();
		cab1.setCabId(1);
		cab1.setCarType("Mini");
		cab1.setPerKmRate(10);
		when(cabRepository.save(cab1)).thenReturn(cab1);
		assertEquals(cab1, cabService.insertCab(cab1));
	}
	
   @Test
	public void testUpdateCab() throws CabNotFoundException 
	{	
		Cab cab1 = new Cab();
		cab1.setCabId(1);
		cab1.setCarType("Mini");
		cab1.setPerKmRate(9);
		when(cabRepository.save(cab1)).thenReturn(cab1);
		when(cabRepository.findById(cab1.getCabId())).thenReturn(Optional.of(cab1));
		assertEquals(cab1, cabService.updateCab(cab1));
	}

	@Test
	public void testDeleteCab() throws CabNotFoundException 
	{	
		Cab cab1 = new Cab();
		cab1.setCabId(1);
		cab1.setCarType("Mini");
		cab1.setPerKmRate(9);
		when(cabRepository.findById(cab1.getCabId())).thenReturn(Optional.of(cab1));
		assertEquals(cab1, cabService.deleteCab(1));
	}

}