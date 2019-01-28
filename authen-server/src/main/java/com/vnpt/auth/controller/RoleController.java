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
import com.vnpt.auth.model.UserRoleModel;
import com.vnpt.auth.service.RoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {
	
	@Autowired
	RoleService rolesService;
	
	@RequestMapping(value="/getAllRoles", method=RequestMethod.GET)
	public List<UserRoleModel> getAllRole(){
		List<UserRoleModel> listAllUserRoles = rolesService.getAllRoles();
		return listAllUserRoles;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<List<UserRoleModel>> listUser(@RequestParam(value="keyword", required=false)String keyword){
		List<UserRoleModel> listRoles = rolesService.listRoles(keyword != null ? keyword : "");
		return ResponseEntity.status(HttpStatus.OK).body(listRoles);
	}
	
	@RequestMapping(value="/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<UserRoleModel>  viewInfo(@PathVariable Long roleId){
		UserRoleModel userRoleModel = rolesService.getRoleById(roleId);
		return ResponseEntity.status(HttpStatus.OK).body(userRoleModel);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<String> addRole(@RequestBody UserRoleModel userRoleModel){
		String returnMessage = rolesService.addRole(userRoleModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
	
	@RequestMapping(value="/delete/{roleId}", method = RequestMethod.POST)
	public ResponseEntity<String>  deleteRole(@PathVariable Long roleId){
		String returnMessage = rolesService.deleteRole(roleId);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public ResponseEntity<String> editRole(@RequestBody UserRoleModel userRoleModel){
		String returnMessage = rolesService.editRole(userRoleModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
}
