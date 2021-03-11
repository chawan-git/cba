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
import javax.validation.constraints.Pattern;

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
	@Pattern(regexp = "[0-9]", message = "Invalid Id number")
	int cabId;
	
	@ApiModelProperty(required = true, example = "SUV")
	//The Column annotation is used to specify the mapped column for a persistent property or field
	@Column
	@Pattern(regexp = "[a-zA-Z0-9]", message ="Invalid CarType")
	String carType;
	@ApiModelProperty(required = true, example = "11.0")
	@NotNull
	@Column
	@Pattern(regexp ="^(\\d+(\\.\\d{0,2})?|\\.?\\d{1,2})$", message = "not a valid entry")
	float perKmRate;
	
}