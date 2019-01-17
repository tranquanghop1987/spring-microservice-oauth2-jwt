package com.vnpt.webappraw.model;

import java.util.List;

public class UserLoginModel {
	private String username;
	private String password;
	private List<String> listRoles;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getListRoles() {
		return listRoles;
	}
	public void setListRoles(List<String> listRoles) {
		this.listRoles = listRoles;
	}
	
}
