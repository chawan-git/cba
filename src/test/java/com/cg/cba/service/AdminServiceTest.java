/**
 * 
 */
package com.cg.cba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.cba.entities.Admin;
import com.cg.cba.exception.AdminAlreadyExsistsException;
import com.cg.cba.exception.AdminNotFoundException;
import com.cg.cba.repository.IAdminRepository;

/**
 * @author arc
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminServiceTest {

	@Autowired
	private IAdminService adminService;
	@MockBean
	private IAdminRepository adminRepository;
	
	@Test
	public void testInsertAdmin() throws AdminAlreadyExsistsException {
		Admin admin1 = new Admin();
		admin1.setAdminId(1);
		admin1.setUsername("a1");
		admin1.setPassword("pa1");
		admin1.setAddress("Addr of admin1");
		admin1.setEmail("admin1@cg.com");
		admin1.setMobileNumber("90350");
		when(adminRepository.save(admin1)).thenReturn(admin1);
		assertEquals(admin1, adminService.insertAdmin(admin1));
	}
	
	@Test
	public void testUpdateAdmin() throws AdminAlreadyExsistsException, AdminNotFoundException {
		Admin admin1 = new Admin();
		admin1.setAdminId(1);
		admin1.setUsername("a1");
		admin1.setPassword("pa1");
		admin1.setAddress("Addr of admin1");
		admin1.setEmail("admin1@cg.com");
		admin1.setMobileNumber("90350");
				
		when(adminRepository.save(admin1)).thenReturn(admin1);
		when(adminRepository.findById(admin1.getAdminId())).thenReturn(Optional.of(admin1));
		assertEquals(admin1, adminService.updateAdmin(admin1));
	}
	
	@Test
	public void testDeleteAdmin() throws AdminNotFoundException {
		Admin admin1 = new Admin();
		admin1.setAdminId(1);
		admin1.setUsername("a1");
		admin1.setPassword("pa1");
		admin1.setAddress("Addr of admin1");
		admin1.setEmail("admin1@cg.com");
		admin1.setMobileNumber("90350");
		
		when(adminRepository.findById(admin1.getAdminId())).thenReturn(Optional.of(admin1));
		assertEquals(admin1, adminService.deleteAdmin(1));
	}
	
}
