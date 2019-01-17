package com.vnpt.webappraw.model;

import java.util.Set;

public class AuthModel {
	private String accessToken;
	private Set<String> roles;
	private String refreshToken;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refeshToken) {
		this.refreshToken = refeshToken;
	}
	
}
