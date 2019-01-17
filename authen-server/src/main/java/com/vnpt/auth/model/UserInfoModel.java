package com.vnpt.auth.model;

import java.util.List;

public class UserInfoModel {
	private String username;
	private String email;
	private String role;
	private Long roleId;
	private List<String> listUserFunction;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getListUserFunction() {
		return listUserFunction;
	}
	public void setListUserFunction(List<String> listUserFunction) {
		this.listUserFunction = listUserFunction;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
