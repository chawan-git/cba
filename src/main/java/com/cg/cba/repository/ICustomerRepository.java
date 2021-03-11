/**
 * Author: Arfath Pasha
 */
package com.cg.cba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.cba.entities.Customer;

//ICustomRepository extends JpaRepository in order to get ready-made methods for performing CRUD operations.
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	// This method will fetch All customers from database.
	@Query("SELECT customer FROM Customer customer")
	public List<Customer> viewCustomers();

	// This method will fetch customer data based on the custId from database.
	@Query("SELECT customer FROM Customer customer WHERE customerId = ?1")
	public Customer viewCustomer(int customerId);
	@Query("SELECT customer FROM Customer customer WHERE customer.email = ?1")
	public Customer findByEmail(String email);
	@Query("SELECT customer FROM Customer customer WHERE customer.username = ?1")
	public Customer findByUsername(String username);
	@Query("SELECT customer FROM Customer customer WHERE customer.mobileNumber = ?1")
	public Customer findByMobileNumber(String mobileNumber);
}
