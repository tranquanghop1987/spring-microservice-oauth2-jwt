package com.vnpt.auth.model;

import java.util.List;

public class VnptUserModel {
	private String username;
	private String email;
	private String password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getListUserFunction() {
		return listUserFunction;
	}
	public void setListUserFunction(List<String> listUserFunction) {
		this.listUserFunction = listUserFunction;
	}
}
