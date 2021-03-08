/**
 * Author: Ankitha Suraksha
 */
package com.cg.cba.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author arc
 *
 */
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
