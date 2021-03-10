/**
 * 
 */
package com.cg.cba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
//Testing of the Admin Module
@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminServiceTest {

	//Loose coupling the adminService
	@Autowired
	private IAdminService adminService;
	//Mocking the adminRepository database
	@MockBean
	private IAdminRepository adminRepository;
	//Testing the insertAdmin functionality
	
	Admin admin1 = null;
	
	@BeforeEach
	public void testBefore() {
		admin1 = new Admin();
		admin1.setAdminId(1);
		admin1.setUsername("a1");
		admin1.setPassword("pa1");
		admin1.setAddress("Addr of admin1");
		admin1.setEmail("admin1@cg.com");
		admin1.setMobileNumber("90350");
	}
	
	@Test
	public void testInsertAdmin() throws AdminAlreadyExsistsException {
		when(adminRepository.save(admin1)).thenReturn(admin1);
		assertEquals(admin1, adminService.insertAdmin(admin1));
	}
	//Testing the updateAdmin functionality
	@Test
	public void testUpdateAdmin() throws AdminAlreadyExsistsException, AdminNotFoundException {
		
		when(adminRepository.save(admin1)).thenReturn(admin1);
		when(adminRepository.findById(admin1.getAdminId())).thenReturn(Optional.of(admin1));
		assertEquals(admin1, adminService.updateAdmin(admin1));
	}
	//Testing the deleteAdmin functionality
	@Test
	public void testDeleteAdmin() throws AdminNotFoundException {

		
		when(adminRepository.findById(admin1.getAdminId())).thenReturn(Optional.of(admin1));
		assertEquals(admin1, adminService.deleteAdmin(1));
	}
	//Testing the AdminNotFoundException exception
	@Test
	public void testAdminNotFoundException() {

		when(adminRepository.findById(admin1.getAdminId())).thenThrow(new AdminNotFoundException("Admin Not Found"));
		assertThrows(AdminNotFoundException.class, () -> adminService.updateAdmin(admin1));
		
	}
	
	@AfterEach
	public void testAfter() {
		admin1 = null;
	}
}
