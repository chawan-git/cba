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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int tripBookingId;
	
	@Column(nullable = false)
	private int customerId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "driver_fk")
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
	
	private boolean status;
	
	private float distanceInKm;
	
	private float bill;	
	
}