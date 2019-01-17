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

import com.vnpt.webappraw.model.CategoryModel;
import com.vnpt.webappraw.model.UserSessionModel;

@Service
public class CategoryService {
	
	public List<CategoryModel> searchCategory(String keyword, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        ResponseEntity<CategoryModel[]> response = restTemplate.exchange("http://localhost:8686/category/list?keyword="+keyword, HttpMethod.GET, entity, CategoryModel[].class);
        List<CategoryModel> returnlist = Arrays.asList(response.getBody()); 
        return returnlist;
	}
	
	public CategoryModel getCategoryDetail(Long categoryId, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8686/category/"+categoryId);

        ResponseEntity<CategoryModel> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, CategoryModel.class);
        return response.getBody();
	}
	
	public String addCategory(CategoryModel categoryModel, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());

        HttpEntity<CategoryModel> request = new HttpEntity<CategoryModel>(categoryModel, headers);

        String response = restTemplate.postForObject("http://localhost:8686/category/add", request, String.class);
		return response;
	}
	
	public String editCategory(CategoryModel categoryModel, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());

        HttpEntity<CategoryModel> request = new HttpEntity<CategoryModel>(categoryModel, headers);

        String response = restTemplate.postForObject("http://localhost:8686/category/edit", request, String.class);
		return response;
	}
	
	public String deleteCategory(Long categoryId, HttpSession session){
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + userSession.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8686/category/delete/"+categoryId);

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, entity, String.class);
        return response.getBody();
	}
}
