/**
 * Author: Arfath Pasha
 */
package com.cg.cba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.cba.entities.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("SELECT customer FROM Customer customer")
	public List<Customer> viewCustomers();
	
	@Query("SELECT customer FROM Customer customer WHERE customerId = ?1")
	public Customer viewCustomer(int customerId);
}
