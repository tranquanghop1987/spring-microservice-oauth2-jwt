package com.vnpt.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vnpt.auth.entity.UserFunction;
import com.vnpt.auth.entity.UserRole;
import com.vnpt.auth.entity.VnptUser;
import com.vnpt.auth.model.CustomUserDetail;
import com.vnpt.auth.model.VnptUserModel;
import com.vnpt.auth.repository.UserFunctionRepository;
import com.vnpt.auth.repository.VnptUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	VnptUserRepository vnptUserRepository;
	
	@Autowired
	UserFunctionRepository userFunctionRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		VnptUser vnptUser = vnptUserRepository.findOneByUsername(username);
		
		UserRole userRole = vnptUser.getUserRole();
		Long userRoleId = userRole.getUserRoleId();
		List<UserFunction> listUserFunction = userFunctionRepository.findAllByUserRoleId(userRoleId);
		List<String> listUserFunctionStr = new ArrayList<String>();
		listUserFunction.forEach(userFunction -> listUserFunctionStr.add(userFunction.getUserFunctionName()));
		
		VnptUserModel vnptUserModel = new VnptUserModel();
		vnptUserModel.setUsername(vnptUser.getUsername());
		vnptUserModel.setPassword(passwordEncoder.encode(vnptUser.getPassword()));
		vnptUserModel.setEmail(vnptUser.getEmail());
		vnptUserModel.setListUserFunction(listUserFunctionStr);
		
		CustomUserDetail customUserDetail = new CustomUserDetail(vnptUserModel);
		return customUserDetail;
	}

}
