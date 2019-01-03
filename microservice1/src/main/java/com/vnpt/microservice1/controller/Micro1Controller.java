package com.vnpt.microservice1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/micro1/")
public class Micro1Controller {
	@RequestMapping("/test")
	public String testApi() {
		return "Test HopTQ";
	}
	
	@RequestMapping("/test2")
	public String testApi2() {
		return "Test HopTQ2";
	}
}
