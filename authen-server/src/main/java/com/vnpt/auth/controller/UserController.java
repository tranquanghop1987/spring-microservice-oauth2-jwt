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

import com.vnpt.auth.model.UserInfoModel;
import com.vnpt.auth.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<List<UserInfoModel>> listUser(@RequestParam(value="keyword", required=false)String keyword){
		List<UserInfoModel> listUser = userService.listUser(keyword != null ? keyword : "");
		return ResponseEntity.status(HttpStatus.OK).body(listUser);
	}
	
	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	public ResponseEntity<UserInfoModel>  viewInfo(@PathVariable String username){
		UserInfoModel userInfoModel = userService.getUserInfoByUsername(username);
		return ResponseEntity.status(HttpStatus.OK).body(userInfoModel);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody UserInfoModel userInfo){
		String returnMessage = userService.addUser(userInfo);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
	
	@RequestMapping(value="/delete/{username}", method = RequestMethod.POST)
	public ResponseEntity<String>  deleteUser(@PathVariable String username){
		String returnMessage = userService.deleteUser(username);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public ResponseEntity<String> editUser(@RequestBody UserInfoModel userInfo){
		String returnMessage = userService.editUser(userInfo);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
}
