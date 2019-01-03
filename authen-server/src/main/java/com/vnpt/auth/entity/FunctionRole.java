package com.vnpt.auth.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the function_role database table.
 * 
 */
@Entity
@Table(name="function_role")
@NamedQuery(name="FunctionRole.findAll", query="SELECT f FROM FunctionRole f")
public class FunctionRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FunctionRolePK id;

	public FunctionRole() {
	}

	public FunctionRolePK getId() {
		return this.id;
	}

	public void setId(FunctionRolePK id) {
		this.id = id;
	}

}