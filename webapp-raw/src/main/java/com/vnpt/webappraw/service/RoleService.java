package com.vnpt.webappraw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vnpt.webappraw.model.UserFunctionModel;
import com.vnpt.webappraw.model.UserRoleModel;
import com.vnpt.webappraw.model.UserSessionModel;

@Service
public class RoleService {
	
	public List<UserRoleModel> getAllRoles(HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8686/auth/role/getAllRoles");

        ResponseEntity<Object> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, Object.class);
        return (List<UserRoleModel>)response.getBody();
	}
	
	public List<UserFunctionModel> getAllFunction(HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8686/auth/user-function/getAllFunctions");

        ResponseEntity<Object> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, Object.class);
        return (List<UserFunctionModel>)response.getBody();
	}
}
