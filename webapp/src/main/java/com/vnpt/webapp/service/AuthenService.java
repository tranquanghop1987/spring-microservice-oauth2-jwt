package com.vnpt.webapp.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import com.vnpt.webapp.model.AuthModel;

@Service
public class AuthenService {
	
	@Autowired
	private ResourceOwnerPasswordAccessTokenProvider provider;
	
	public AuthModel getToken(String username, String password) {
		AuthModel returnValue = new AuthModel();
		
		OAuth2AccessToken oauth2AccessToken = obtainToken(username, password);
		String accessToken = oauth2AccessToken.getValue();
		Set<String> roles = oauth2AccessToken.getScope();
		String refreshToken = oauth2AccessToken.getRefreshToken().getValue();
		
		returnValue.setAccessToken(accessToken);
		returnValue.setRoles(roles);
		returnValue.setRefreshToken(refreshToken);
		
		return returnValue;
	}
	
	@Bean
	public ResourceOwnerPasswordAccessTokenProvider initResourceOwner() {
		ResourceOwnerPasswordAccessTokenProvider rsown = new ResourceOwnerPasswordAccessTokenProvider();
		return rsown;
	}
	
	private OAuth2AccessToken obtainToken(String username, String password) {
	    ResourceOwnerPasswordResourceDetails passwordResourceDetails = new ResourceOwnerPasswordResourceDetails();
	    passwordResourceDetails.setUsername(username);
	    passwordResourceDetails.setPassword(password);
	    passwordResourceDetails.setClientId("acme");
	    passwordResourceDetails.setClientSecret("secret");
	    passwordResourceDetails.setAccessTokenUri("http://localhost:8686/auth/oauth/token");
	    DefaultAccessTokenRequest defaultAccessTokenRequest = new DefaultAccessTokenRequest();
	    OAuth2AccessToken token;
	    try {
	        token = provider.obtainAccessToken(passwordResourceDetails, defaultAccessTokenRequest);
	    } catch (OAuth2AccessDeniedException accessDeniedException) {
	        throw new BadCredentialsException("Invalid credentials", accessDeniedException);
	    }

	    return token;
	}
	
}
