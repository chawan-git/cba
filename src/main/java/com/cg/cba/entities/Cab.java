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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cab {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int cabId;
	@Column
	String carType;
	@Column
	float perKmRate;
	
}