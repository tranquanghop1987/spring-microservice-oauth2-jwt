package com.vnpt.microservice2.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	@GetMapping("/test")
	public String testApi() {
		return "Test HopTQ";
	}
	
	@GetMapping("/test2")
	public String testApi2() {
		return "Test HopTQ 2";
	}
}
