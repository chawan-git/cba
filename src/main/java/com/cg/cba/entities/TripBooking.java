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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//using Lombok api to generate setters and getters
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//creating a table in the database
@Entity

//providing description for swagger api

//POJO class representing a table in database
@ApiModel
public class TripBooking {
	
	//*********creating columns for the database and providing notes for swagger api *******//
	
	//Making tripBookingId as primary key and auto incrementing it
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Unique identifier of the TripBooking, No two trips can have the same TripBookingId.", example = "1", required = true)
	private int tripBookingId;
	
	//implementing Has a relationship of Customer Class and implementing one to one relationship
	@OneToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
	@ApiModelProperty(notes = "All Driver detalis",  required = true)
	private Customer customer;
	
	//implementing Has a relationship of Driver Class
	@ApiModelProperty(notes = "All Driver detalis", required = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private Driver driver;
	
	@ApiModelProperty(notes = "Starting Location of the Trip", example = "Bengaluru", required = true)
	@Column(nullable = false)
	private String fromLocation;
	
	@Column(nullable = false)
	@ApiModelProperty(notes = "Destination of the Trip", example = "Mysore", required = true)
	private String toLocation;
		
	@JsonFormat(pattern = "yyyy-MM-dd H:m:s")
	@Column(nullable = false)
	@ApiModelProperty(notes = "Start Time of the Trip", example = "2021-03-09 09:00:00", required = true)
	public  LocalDateTime fromDateTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd H:m:s")
	@Column(nullable = false)
	@ApiModelProperty(notes = "End Time of the Trip", example = "2021-03-09 18:00:00", required = true)
	private LocalDateTime toDateTime;
	
	@Column
	@ApiModelProperty(notes = "status of the Trip", example = "true", required = true)
	private boolean status;
	
	@Column
	@ApiModelProperty(notes = "Distance of Trip", example = "12.5", required = true)
	private float distanceInKm;
	
	@Column
	@ApiModelProperty(notes = "Trip Bill in Rupees", example = "155.55", required = false)
	private float bill;	
	
}