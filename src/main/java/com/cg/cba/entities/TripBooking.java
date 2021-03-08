/**
 * Author: Farooq Shaik Mohammad
 */
package com.cg.cba.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer tripBookingId;
	@Column
	private int customerId;
	@ManyToOne
	@JoinColumn
	private Driver driver;
	@Column
	private String fromLocation;
	@Column
	private String toLocation;
	@Column
	public  LocalDateTime fromDateTime;
	@Column
	private LocalDateTime toDateTime; 
	@Column
	private boolean status;
	@Column
	private float distanceInKm;
	@Column
	private float bill;
	
	
}
