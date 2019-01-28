package com.vnpt.webappraw.service;

import java.util.Arrays;
import java.util.List;

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
public class FunctionService {
	
	private static final String PREFIX_URL_FUNCTION = "http://localhost:8686/auth/user-function/";
	
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
	
	public List<UserFunctionModel> searchFunction(String keyword, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        ResponseEntity<UserFunctionModel[]> response = restTemplate.exchange(PREFIX_URL_FUNCTION + "list?keyword="+keyword, HttpMethod.GET, entity, UserFunctionModel[].class);
        List<UserFunctionModel> returnlist = Arrays.asList(response.getBody()); 
        return returnlist;
	}
	
	public UserFunctionModel getFunctionInfo(Long userFunctionId, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(PREFIX_URL_FUNCTION + userFunctionId);

        ResponseEntity<UserFunctionModel> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, UserFunctionModel.class);
        return response.getBody();
	}
	
	public String addFunction(UserFunctionModel userFunctionModel, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());

        HttpEntity<UserFunctionModel> request = new HttpEntity<UserFunctionModel>(userFunctionModel, headers);

        String response = restTemplate.postForObject(PREFIX_URL_FUNCTION + "add", request, String.class);
		return response;
	}
	
	public String editFunction(UserFunctionModel userFunctionModel, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());

        HttpEntity<UserFunctionModel> request = new HttpEntity<UserFunctionModel>(userFunctionModel, headers);

        String response = restTemplate.postForObject(PREFIX_URL_FUNCTION + "edit", request, String.class);
		return response;
	}
	
	public String deleteFunction(Long userFunctionId, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(PREFIX_URL_FUNCTION +"/delete/"+userFunctionId);

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, entity, String.class);
        return response.getBody();
	}
}
