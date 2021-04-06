package com.vnpt.webappraw.service;

import java.util.Arrays;
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

import com.vnpt.webappraw.model.CategoryModel;
import com.vnpt.webappraw.model.UserFunctionModel;
import com.vnpt.webappraw.model.UserRoleModel;
import com.vnpt.webappraw.model.UserSessionModel;

@Service
public class RoleService {
	
	private static final String PREFIX_URL_ROLE = "http://localhost:8686/auth/role/";
	
	public List<UserRoleModel> getAllRoles(HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(PREFIX_URL_ROLE + "getAllRoles");

        ResponseEntity<Object> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, Object.class);
        return (List<UserRoleModel>)response.getBody();
	}
	
	public List<UserRoleModel> searchUserRoles(String keyword, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        ResponseEntity<UserRoleModel[]> response = restTemplate.exchange(PREFIX_URL_ROLE + "list?keyword="+keyword, HttpMethod.GET, entity, UserRoleModel[].class);
        List<UserRoleModel> returnlist = Arrays.asList(response.getBody()); 
        return returnlist;
	}
	
	public UserRoleModel getRoleInfo(Long roleId, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(PREFIX_URL_ROLE + roleId);

        ResponseEntity<UserRoleModel> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, UserRoleModel.class);
        return response.getBody();
	}
	
	public String addRole(UserRoleModel userRoleModel, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());

        HttpEntity<UserRoleModel> request = new HttpEntity<UserRoleModel>(userRoleModel, headers);

        String response = restTemplate.postForObject(PREFIX_URL_ROLE + "add", request, String.class);
		return response;
	}
	
	public String editRole(UserRoleModel userRoleModel, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());

        HttpEntity<UserRoleModel> request = new HttpEntity<UserRoleModel>(userRoleModel, headers);

        String response = restTemplate.postForObject(PREFIX_URL_ROLE + "edit", request, String.class);
		return response;
	}
	
	public String deleteRole(Long roleId, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(PREFIX_URL_ROLE +"/delete/"+roleId);

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, entity, String.class);
        return response.getBody();
	}
}