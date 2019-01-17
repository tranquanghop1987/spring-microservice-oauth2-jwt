package com.vnpt.auth.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_role_id")
	private Long userRoleId;

	private String description;

	@Column(name="user_role_name")
	private String userRoleName;

	//bi-directional many-to-one association to VnptUser
	@OneToMany(mappedBy="userRole")
	private List<VnptUser> vnptUsers;

	public UserRole() {
	}

	public Long getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserRoleName() {
		return this.userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public List<VnptUser> getVnptUsers() {
		return this.vnptUsers;
	}

	public void setVnptUsers(List<VnptUser> vnptUsers) {
		this.vnptUsers = vnptUsers;
	}

	public VnptUser addVnptUser(VnptUser vnptUser) {
		getVnptUsers().add(vnptUser);
		vnptUser.setUserRole(this);

		return vnptUser;
	}

	public VnptUser removeVnptUser(VnptUser vnptUser) {
		getVnptUsers().remove(vnptUser);
		vnptUser.setUserRole(null);

		return vnptUser;
	}

}