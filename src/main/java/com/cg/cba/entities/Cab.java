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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Cab {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "cabId will be auto-generated", required = false, position = 1)
	int cabId;
	@Column
	@NotBlank(message = "Car Type can't be blank!")
	@ApiModelProperty(notes = "Car Type can't be null or blank", example = "SUV", required = true, position = 2)
	String carType;
	@Column
	@Min(1)
	@NotBlank(message = "Rate can't be blank!")
	@ApiModelProperty(notes = "Rate can't be null or blank", example = "10", required = true, position = 3)
	float perKmRate;
	
}