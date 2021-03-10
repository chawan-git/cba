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
public class Driver extends AbstractUser{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int driverId;
	@Column(unique = true, nullable = false)
	private String licenseNo;
	@OneToOne(fetch = FetchType.EAGER)
	private Cab cab;
	@Column
	private float rating;

}