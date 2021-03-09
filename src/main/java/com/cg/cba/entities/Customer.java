/**
 * Author: Arfath Pasha
 */
package com.cg.cba.entities;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends AbstractUser{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int customerId;
}
