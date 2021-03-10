/**
 * Author: Bharat Singh
 */
package com.cg.cba.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//creating a table in the database
@Entity
@Table

//using Lombok api to generate setters and getters
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Driver extends AbstractUser{

	//*********creating columns for the database and providing notes for swagger api *******//
	
	//Making "driverId as primary key and auto incrementing it
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "driverId will be autogenerated", required = false)
	private int driverId;

	@Column(unique = true, nullable = false)
	@ApiModelProperty(notes = "licenseNo can not be null or blank", example = "L12MBok", required = true)
	private String licenseNo;

	//implementing Has a relationship of Cab Class and implementing one to one relationship
	@OneToOne(fetch = FetchType.EAGER)
	private Cab cab;

	@ApiModelProperty(required = true, example = "4.5")
	@NotNull
	@Column
	private float rating;

}