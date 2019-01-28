package com.vnpt.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vnpt.auth.model.UserFunctionModel;
import com.vnpt.auth.model.UserRoleModel;
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
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<List<UserFunctionModel>> listUserFunction(@RequestParam(value="keyword", required=false)String keyword){
		List<UserFunctionModel> listUserFunction = userFunctionService.listUserFunction(keyword != null ? keyword : "");
		return ResponseEntity.status(HttpStatus.OK).body(listUserFunction);
	}
	
	@RequestMapping(value="/{userFunctionId}", method = RequestMethod.GET)
	public ResponseEntity<UserFunctionModel>  getUserFunctionById(@PathVariable Long userFunctionId){
		UserFunctionModel userRoleModel = userFunctionService.getUserFunctionById(userFunctionId);
		return ResponseEntity.status(HttpStatus.OK).body(userRoleModel);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<String> addFunction(@RequestBody UserFunctionModel userFunctionModel){
		String returnMessage = userFunctionService.addFunction(userFunctionModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
	
	@RequestMapping(value="/delete/{userFunctionId}", method = RequestMethod.POST)
	public ResponseEntity<String>  deleteFunction(@PathVariable Long userFunctionId){
		String returnMessage = userFunctionService.deleteFunction(userFunctionId);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public ResponseEntity<String> editFunction(@RequestBody UserFunctionModel userFunctionModel){
		String returnMessage = userFunctionService.editFunction(userFunctionModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
}
