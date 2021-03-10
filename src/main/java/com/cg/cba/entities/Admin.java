/**
 * Author: Ashutosh Rao Chawan U
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

/**
 * @author arc
 *
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin extends AbstractUser{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	
}