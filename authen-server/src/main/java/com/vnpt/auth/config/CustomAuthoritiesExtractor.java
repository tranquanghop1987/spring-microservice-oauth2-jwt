package com.vnpt.auth.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthoritiesExtractor implements AuthoritiesExtractor {

	@Override
	public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
