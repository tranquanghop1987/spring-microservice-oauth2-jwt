package com.vnpt.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnpt.auth.entity.UserFunction;
import com.vnpt.auth.entity.UserRole;
import com.vnpt.auth.entity.VnptUser;
import com.vnpt.auth.model.UserInfoModel;
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
	
	public List<UserRoleModel> listRoles(String keyword) {
		List<UserRoleModel> returnListRoles = new ArrayList<>();
		List<UserRole> listUserRoles = (List<UserRole>)userRoleRepository.searchByKeyword(keyword);
		listUserRoles.forEach(item->{
			UserRoleModel userRoleModel = new UserRoleModel();
			userRoleModel.setUserRoleId(item.getUserRoleId());
			userRoleModel.setUserRoleName(item.getUserRoleName());
			userRoleModel.setDescription(item.getDescription());
			returnListRoles.add(userRoleModel);
		});
		return returnListRoles;
	}
	
	public UserRoleModel getRoleById(Long userRoleId) {
		UserRoleModel userRoleModel = new UserRoleModel();
		
		UserRole userRole = userRoleRepository.findOneByUserRoleId(userRoleId);
		
		userRoleModel.setUserRoleId(userRole.getUserRoleId());
		userRoleModel.setUserRoleName(userRole.getUserRoleName());
		userRoleModel.setDescription(userRole.getDescription());
		
		return userRoleModel;
	}
	
	public String addRole(UserRoleModel userRoleModel){
		UserRole userRole = new UserRole();
		userRole.setUserRoleId(userRoleModel.getUserRoleId());
		userRole.setUserRoleName(userRoleModel.getUserRoleName());
		userRole.setDescription(userRoleModel.getDescription());
		
		UserRole userRoleAfterSave = userRoleRepository.save(userRole);
		
		if(userRoleAfterSave != null) {
			return "Thêm chức danh thành công";
		}else {
			return "Thêm chức danh thất bại";
		}
	}
	
	public String editRole(UserRoleModel userRoleModel){
		Boolean isExist = userRoleRepository.existsById(userRoleModel.getUserRoleId());
		if(isExist) {
			UserRole userRole = new UserRole();
			userRole.setUserRoleId(userRoleModel.getUserRoleId());
			userRole.setUserRoleName(userRoleModel.getUserRoleName());
			userRole.setDescription(userRoleModel.getDescription());
			
			UserRole userRoleAfterSave = userRoleRepository.save(userRole);
			
			if(userRoleAfterSave != null) {
				return "Thêm chức danh thành công";
			}else {
				return "Thêm chức danh thất bại";
			}
		}else {
			return "Chức danh không tồn tại";
		}
	}
	
	public String deleteRole(Long userRoleId){
		userRoleRepository.deleteById(userRoleId);
		return "Xóa chức danh thành công";
	}
	
}
