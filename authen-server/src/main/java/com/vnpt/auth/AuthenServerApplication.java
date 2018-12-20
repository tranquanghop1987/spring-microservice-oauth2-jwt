package com.vnpt.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthenServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenServerApplication.class, args);
	}

}

