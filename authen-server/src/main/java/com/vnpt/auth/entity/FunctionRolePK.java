package com.vnpt.auth.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the function_role database table.
 * 
 */
@Embeddable
public class FunctionRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_role_id")
	private Integer userRoleId;

	@Column(name="user_function_id")
	private Integer userFunctionId;

	public FunctionRolePK() {
	}
	public Integer getUserRoleId() {
		return this.userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public Integer getUserFunctionId() {
		return this.userFunctionId;
	}
	public void setUserFunctionId(Integer userFunctionId) {
		this.userFunctionId = userFunctionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FunctionRolePK)) {
			return false;
		}
		FunctionRolePK castOther = (FunctionRolePK)other;
		return 
			this.userRoleId.equals(castOther.userRoleId)
			&& this.userFunctionId.equals(castOther.userFunctionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userRoleId.hashCode();
		hash = hash * prime + this.userFunctionId.hashCode();
		
		return hash;
	}
}