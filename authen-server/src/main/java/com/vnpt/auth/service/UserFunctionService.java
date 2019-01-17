package com.vnpt.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnpt.auth.entity.UserFunction;
import com.vnpt.auth.model.UserFunctionModel;
import com.vnpt.auth.repository.UserFunctionRepository;

@Service
public class UserFunctionService {
	
	@Autowired
	UserFunctionRepository userFunctionRepository;
	
	public List<UserFunctionModel> getAllFunction(){
		List<UserFunction> listUserFunction = (List<UserFunction>)userFunctionRepository.findAll();
		List<UserFunctionModel> listUserFunctionModel = new ArrayList<>();
		listUserFunction.forEach(item->{
			UserFunctionModel userFunctionModel = new UserFunctionModel();
			userFunctionModel.setUserFunctionId(item.getUserFunctionId());
			userFunctionModel.setUserFunctionName(item.getUserFunctionName());
			userFunctionModel.setDescription(item.getDescription());
			listUserFunctionModel.add(userFunctionModel);
		});
		return listUserFunctionModel;
	}
}
