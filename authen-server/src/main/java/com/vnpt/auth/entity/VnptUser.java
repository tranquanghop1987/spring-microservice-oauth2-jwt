package com.vnpt.auth.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vnpt_user database table.
 * 
 */
@Entity
@Table(name="vnpt_user")
@NamedQuery(name="VnptUser.findAll", query="SELECT v FROM VnptUser v")
public class VnptUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String email;

	private String password;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="user_role_id")
	private UserRole userRole;

	public VnptUser() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}