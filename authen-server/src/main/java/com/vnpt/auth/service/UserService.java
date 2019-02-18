package com.vnpt.auth.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnpt.auth.repository.UserFunctionRepositoryJdbc;
import com.vnpt.auth.repository.VnptUserRepositoryJdbc;

@Service
public class UserService {

	@Autowired
	VnptUserRepositoryJdbc vnptUserRepository;
	
	@Autowired
	UserFunctionRepositoryJdbc userFunctionRepository;
	
	public Map<String,Object> getUserInfoByUsername(String username) {
		
		Map<String,Object> vnptUser = vnptUserRepository.findOneByUsername(username);
		
		Integer userRoleId = (Integer)vnptUser.get("user_role_id");
		List<String> listUserFunction = userFunctionRepository.findAllByUserRoleId(userRoleId);
		
		vnptUser.put("list_function", listUserFunction);
		
		return vnptUser;
	}
	
}
