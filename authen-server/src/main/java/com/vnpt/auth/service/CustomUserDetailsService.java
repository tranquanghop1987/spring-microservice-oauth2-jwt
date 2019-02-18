package com.vnpt.auth.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vnpt.auth.model.CustomUserDetail;
import com.vnpt.auth.model.VnptUserModel;
import com.vnpt.auth.repository.UserFunctionRepositoryJdbc;
import com.vnpt.auth.repository.VnptUserRepositoryJdbc;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	VnptUserRepositoryJdbc vnptUserRepository;
	
	@Autowired
	UserFunctionRepositoryJdbc userFunctionRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Map<String,Object> vnptUser = vnptUserRepository.findOneByUsername(username);
		
		Integer userRoleId = (Integer)vnptUser.get("user_role_id");
		List<String> listUserFunction = userFunctionRepository.findAllByUserRoleId(userRoleId);
		
		VnptUserModel vnptUserModel = new VnptUserModel();
		vnptUserModel.setUsername((String)vnptUser.get("username"));
		vnptUserModel.setPassword(passwordEncoder.encode((String)vnptUser.get("password")));
		vnptUserModel.setEmail((String)vnptUser.get("email"));
		vnptUserModel.setListUserFunction(listUserFunction);
		
		CustomUserDetail customUserDetail = new CustomUserDetail(vnptUserModel);
		return customUserDetail;
	}

}
