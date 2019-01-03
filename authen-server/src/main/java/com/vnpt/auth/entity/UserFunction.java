package com.vnpt.auth.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_function database table.
 * 
 */
@Entity
@Table(name="user_function")
@NamedQuery(name="UserFunction.findAll", query="SELECT u FROM UserFunction u")
public class UserFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_function_id")
	private Integer userFunctionId;

	private String description;

	@Column(name="user_function_name")
	private String userFunctionName;

	public UserFunction() {
	}

	public Integer getUserFunctionId() {
		return this.userFunctionId;
	}

	public void setUserFunctionId(Integer userFunctionId) {
		this.userFunctionId = userFunctionId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserFunctionName() {
		return this.userFunctionName;
	}

	public void setUserFunctionName(String userFunctionName) {
		this.userFunctionName = userFunctionName;
	}

}