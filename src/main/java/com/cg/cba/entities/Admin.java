/**
 * Author: Ashutosh Rao Chawan U
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

/**
 * @author arc
 *
 */

//Admin POJO Class is inherited from the AbstractUser
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel
public class Admin extends AbstractUser{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Admin ID will be auto-generated", required = false)
	private int adminId;
	
}