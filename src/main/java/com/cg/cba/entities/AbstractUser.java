/**
 * Author: Ankitha Suraksha
 */
package com.cg.cba.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author arc
 *
 */
//The Abstract User POJO Class which the customer, driver and admin will inherit
@Getter
@Setter
@ApiModel
@MappedSuperclass
public abstract class AbstractUser {

	@Column(unique = true, nullable = false)
	@ApiModelProperty(notes = "username should be unique", required = true)//swagger 
	private String username;
	
	@Column(nullable = false)
	@NotNull(message = "password can not be null")
	@Pattern(regexp = ".{6}.*", message = "password should consist minimum six characters")
	@ApiModelProperty(notes = "password should consist of minimum six characters", example = "vision@4512", required = true)
	private String password;
	
	@Column(length =60)
	private String address;
	
	@NotNull(message = "mobile number can not be null")
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message = "Invalid mobile number")
	@ApiModelProperty(notes = "mobile number should be valid", example = "97658545256", required = true)
	@Column
	private String mobileNumber;
	
	@NotNull(message = "email can not be null")
	@Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "Invalid email address")
	@ApiModelProperty(notes = "email should be valid", example = "lucky@gmail.com", required = true)
	@Column(unique = true, nullable = false)
	private String email;
}