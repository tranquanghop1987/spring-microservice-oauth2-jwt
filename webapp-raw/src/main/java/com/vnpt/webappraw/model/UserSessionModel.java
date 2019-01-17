package com.vnpt.webappraw.model;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UserSessionModel {
	private String username;
	private String email;
	private String role;
	private List<String> listUserFunction;
	private String accessToken;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
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
	
}
