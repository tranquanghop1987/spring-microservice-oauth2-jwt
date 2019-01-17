package com.vnpt.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vnpt.auth.entity.UserFunction;
import com.vnpt.auth.entity.UserRole;
import com.vnpt.auth.entity.VnptUser;
import com.vnpt.auth.model.UserInfoModel;
import com.vnpt.auth.model.VnptUserModel;
import com.vnpt.auth.repository.UserFunctionRepository;
import com.vnpt.auth.repository.UserRoleRepository;
import com.vnpt.auth.repository.VnptUserRepository;

@Service
public class UserService {
	@Autowired
	VnptUserRepository vnptUserRepository;
	
	@Autowired
	UserFunctionRepository userFunctionRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	public UserInfoModel getUserInfoByUsername(String username) {
		UserInfoModel userInfoResponseModel = new UserInfoModel();
		
		VnptUser vnptUser = vnptUserRepository.findOneByUsername(username);
		
		UserRole userRole = vnptUser.getUserRole();
		Long userRoleId = userRole.getUserRoleId();
		List<UserFunction> listUserFunction = userFunctionRepository.findAllByUserRoleId(userRoleId);
		List<String> listUserFunctionStr = new ArrayList<String>();
		listUserFunction.forEach(userFunction -> listUserFunctionStr.add(userFunction.getUserFunctionName()));
		
		userInfoResponseModel.setUsername(vnptUser.getUsername());
		userInfoResponseModel.setEmail(vnptUser.getEmail());
		userInfoResponseModel.setRole(userRole.getUserRoleName());
		userInfoResponseModel.setRoleId(userRoleId);
		userInfoResponseModel.setListUserFunction(listUserFunctionStr);
		
		return userInfoResponseModel;
	}
	
	public List<UserInfoModel> listUser(String keyword) {
		List<UserInfoModel> returnList = new ArrayList<UserInfoModel>();
		List<VnptUser> listVnptUser = vnptUserRepository.searchUsers(keyword);
		for (VnptUser vnptUser : listVnptUser) {
			UserInfoModel userInfoResponseModel = new UserInfoModel();
			userInfoResponseModel.setUsername(vnptUser.getUsername());
			userInfoResponseModel.setEmail(vnptUser.getEmail());
			userInfoResponseModel.setRole(vnptUser.getUserRole().getUserRoleName());
			userInfoResponseModel.setListUserFunction(new ArrayList<>());
			returnList.add(userInfoResponseModel);
		}
		return returnList;
	}
	
	public String addUser(UserInfoModel userInfo){
		VnptUser vnptUser = new VnptUser();
		vnptUser.setUsername(userInfo.getUsername());
		vnptUser.setEmail(userInfo.getEmail());
		vnptUser.setPassword("12345678");
		
		UserRole userRole = userRoleRepository.findOneByUserRoleId(userInfo.getRoleId());
		vnptUser.setUserRole(userRole);
		
		VnptUser vnptInserted = vnptUserRepository.save(vnptUser);
		
		if(vnptInserted != null) {
			return "Thêm user thành công";
		}else {
			return "Thêm user thất bại";
		}
		
	}
	
	public String deleteUser(String username){
		vnptUserRepository.deleteById(username);
		return "Xóa user thành công";
	}
	
	public String editUser(UserInfoModel userInfo){
		Boolean isExist = vnptUserRepository.existsById(userInfo.getUsername());
		if(isExist) {
			VnptUser vnptUser = vnptUserRepository.findOneByUsername(userInfo.getUsername());
			vnptUser.setEmail(userInfo.getEmail());
			UserRole userRole = userRoleRepository.findOneByUserRoleId(userInfo.getRoleId());
			vnptUser.setUserRole(userRole);
			VnptUser vnptUserEdited = vnptUserRepository.save(vnptUser);
			if(vnptUserEdited != null) {
				return "Sửa user thành công";
			}else {
				return "Sửa user thất bại";
			}
		}else {
			return "User không tồn tại";
		}
	}
}
