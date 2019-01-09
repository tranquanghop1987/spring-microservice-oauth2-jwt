package com.vnpt.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vnpt.webapp.model.AuthModel;
import com.vnpt.webapp.service.AuthenService;

@Controller
public class MainController {
	
	@Autowired
	AuthenService authenService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/getToken")
	public @ResponseBody AuthModel testToken() {
		AuthModel authModel = authenService.getToken("hoptq", "123");
		return authModel;
	}
}
