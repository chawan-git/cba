/**
 * Author: Ankitha Suraksha
 */
package com.cg.cba.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author arc
 *
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractUser {

	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column
	private String address;
	@Column
	private String mobileNumber;
	@Column(unique = true, nullable = false)
	private String email;
}
