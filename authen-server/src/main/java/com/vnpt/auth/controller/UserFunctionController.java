package com.vnpt.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vnpt.auth.model.UserFunctionModel;
import com.vnpt.auth.service.UserFunctionService;

@RestController
@RequestMapping(value = "/user-function")
public class UserFunctionController {
	
	@Autowired
	UserFunctionService userFunctionService;
	
	@RequestMapping(value="/getAllFunctions", method=RequestMethod.GET)
	public List<UserFunctionModel> getAllFunction(){
		List<UserFunctionModel> listAllUserFunction = userFunctionService.getAllFunction();
		return listAllUserFunction;
	}
}
