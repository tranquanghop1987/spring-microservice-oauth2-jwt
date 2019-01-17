package com.vnpt.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
