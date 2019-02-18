package com.vnpt.auth;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vnpt.auth.service.UserService;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableResourceServer
public class AuthenServerApplication {
	
	@Autowired
	UserService userservice;

	public static void main(String[] args) {
		SpringApplication.run(AuthenServerApplication.class, args);
	}

	@RequestMapping("/user/me")
	public @ResponseBody Map<String,Object> user(Principal user) {
		String username = user.getName();
		Map<String,Object> userInfoResponseModel = userservice.getUserInfoByUsername(username);
		return userInfoResponseModel;
	}
}

