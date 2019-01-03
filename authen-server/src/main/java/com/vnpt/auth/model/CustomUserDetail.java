package com.vnpt.auth.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetail implements UserDetails  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private VnptUserModel vnptUser;
	
	public CustomUserDetail(VnptUserModel vnptUser) {
		this.vnptUser = vnptUser;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> privileges = vnptUser.getListUserFunction();
		return getGrantedAuthorities(privileges);
	}

	@Override
	public String getPassword() {
		return vnptUser.getPassword();
	}

	@Override
	public String getUsername() {
		return vnptUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
