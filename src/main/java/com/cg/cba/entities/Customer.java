/**
 * Author: Arfath Pasha
 */
package com.cg.cba.entities;

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

//customer POJO class extends AbstractUser
//creating a table in the database
@Entity
@Table
@ApiModel
//using Lombok api to generate setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends AbstractUser {

	//Making customerId as primary key and auto incrementing it
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "customerId", example = "1", required = true)
	private int customerId;
}
