package com.vnpt.webappraw.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vnpt.webappraw.model.AuthModel;
import com.vnpt.webappraw.model.UserAddModel;
import com.vnpt.webappraw.model.UserInfoResponseModel;
import com.vnpt.webappraw.model.UserSessionModel;

@Service
public class UserService {
	public AuthModel getToken(String username, String password) {
		AuthModel returnValue = new AuthModel();
		OAuth2AccessToken oauth2AccessToken = obtainToken(username, password);
		
		if(oauth2AccessToken != null) {
			String accessToken = oauth2AccessToken.getValue();
			Set<String> roles = oauth2AccessToken.getScope();
			String refreshToken = oauth2AccessToken.getRefreshToken().getValue();
			
			returnValue.setAccessToken(accessToken);
			returnValue.setRoles(roles);
			returnValue.setRefreshToken(refreshToken);
			
			return returnValue;
		}else {
			return null;
		}
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
	    	ResourceOwnerPasswordAccessTokenProvider rsown = new ResourceOwnerPasswordAccessTokenProvider();
	    	token = rsown.obtainAccessToken(passwordResourceDetails, defaultAccessTokenRequest);
	    } catch (OAuth2AccessDeniedException accessDeniedException) {
	    	return null;
	    }
	    return token;
	}
	
	public UserInfoResponseModel getUserInfo(String accessToken) {
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        ResponseEntity<UserInfoResponseModel> response = restTemplate.exchange("http://localhost:8686/auth/user/me", HttpMethod.GET, entity, UserInfoResponseModel.class);
        return response.getBody();
	}
	
	public List<UserInfoResponseModel> searchListUser(String keyword, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8686/auth/user/list")
                .queryParam("keyword", keyword);

        ResponseEntity<Object> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, Object.class);
        return (List<UserInfoResponseModel>)response.getBody();
	}
	
	public String addUser(UserAddModel userAddModel, HttpSession session) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        
        HttpEntity<UserAddModel> request = new HttpEntity<UserAddModel>(userAddModel, headers);

        String response = restTemplate.postForObject("http://localhost:8686/auth/user/add", request, String.class);
		return response;
	}
	
	public String editUser(UserAddModel userAddModel, HttpSession session) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        
        HttpEntity<UserAddModel> request = new HttpEntity<UserAddModel>(userAddModel, headers);

        String response = restTemplate.postForObject("http://localhost:8686/auth/user/edit", request, String.class);
		return response;
	}
	
	public String deleteUser(String username, HttpSession session) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8686/auth/user/delete/"+username, HttpMethod.POST, request, String.class);
		return response.getBody();
	}
	
	public UserInfoResponseModel findByUsername(String username, HttpSession session) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        ResponseEntity<UserInfoResponseModel> response = restTemplate.exchange("http://localhost:8686/auth/user/"+username, HttpMethod.GET, request, UserInfoResponseModel.class);
		return response.getBody();
	}
	
}
