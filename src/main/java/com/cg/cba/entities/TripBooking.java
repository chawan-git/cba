/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TripBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tripBookingId;
	
	@OneToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Driver driver;
	
	@Column(nullable = false)
	private String fromLocation;
	
	@Column(nullable = false)
	private String toLocation;
		
	@JsonFormat(pattern = "yyyy-MM-dd H:m:s")
	@Column(nullable = false)
	public  LocalDateTime fromDateTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd H:m:s")
	@Column(nullable = false)
	private LocalDateTime toDateTime; 
	@Column
	private boolean status;
	@Column
	private float distanceInKm;
	@Column
	private float bill;	
	
}