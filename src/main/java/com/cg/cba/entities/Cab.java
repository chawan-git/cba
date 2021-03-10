/**
 * Author: D Sri Madhupriya
 */
package com.cg.cba.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*The Entity annotation specifies that the class is an entity and is mapped to a database table*/
@Entity
@Table
/*Getter and Setter annotation defines getter and setter methods*/
@Getter
@Setter
/*To generate a constructor with arguments for all the field, we use the @AllArgsConstructor annotation*/
@AllArgsConstructor
/*The @NoArgsConstructor annotation to generate the default constructor that takes no arguments*/
//This is the Cab Entity
@NoArgsConstructor
@ApiModel
public class Cab {
	@ApiModelProperty(notes = "Cab ID will be auto generated!",required = false)
	//Id annotation is used for representing primary key
	@Id
	/*If we want the primary key value to be generated automatically for us, 
	 * we can add the @GeneratedValue annotation.
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int cabId;
	@ApiModelProperty(required = true, example = "SUV")
	//The Column annotation is used to specify the mapped column for a persistent property or field
	@Column
	String carType;
	@ApiModelProperty(required = true, example = "11.0")
	@NotNull
	@Column
	float perKmRate;
	
}