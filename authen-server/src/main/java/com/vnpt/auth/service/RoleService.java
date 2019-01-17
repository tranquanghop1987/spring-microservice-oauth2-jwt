package com.vnpt.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnpt.auth.entity.UserRole;
import com.vnpt.auth.model.UserRoleModel;
import com.vnpt.auth.repository.UserRoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public List<UserRoleModel> getAllRoles(){
		List<UserRoleModel> returnListRoles = new ArrayList<>();
		List<UserRole> listUserRoles = (List<UserRole>)userRoleRepository.findAll();
		listUserRoles.forEach(item->{
			UserRoleModel userRoleModel = new UserRoleModel();
			userRoleModel.setUserRoleId(item.getUserRoleId());
			userRoleModel.setUserRoleName(item.getUserRoleName());
			userRoleModel.setDescription(item.getDescription());
			returnListRoles.add(userRoleModel);
		});
		return returnListRoles;
	}
}
