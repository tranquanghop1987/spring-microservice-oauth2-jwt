package com.vnpt.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnpt.auth.entity.UserFunction;
import com.vnpt.auth.entity.UserRole;
import com.vnpt.auth.model.UserFunctionModel;
import com.vnpt.auth.model.UserRoleModel;
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
	
	public List<UserFunctionModel> listUserFunction(String keyword){
		List<UserFunction> listUserFunction = (List<UserFunction>)userFunctionRepository.searchByKeyword(keyword);
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
	
	public UserFunctionModel getUserFunctionById(Long userFunctionId){
		UserFunctionModel userFunctionModel = new UserFunctionModel();
		
		UserFunction userFunction = userFunctionRepository.findOneByUserFunctionId(userFunctionId);
		
		userFunctionModel.setUserFunctionId(userFunction.getUserFunctionId());
		userFunctionModel.setUserFunctionName(userFunction.getUserFunctionName());
		userFunctionModel.setDescription(userFunction.getDescription());
		
		return userFunctionModel;
	}
	
	public String addFunction(UserFunctionModel userFunctionModel){
		UserFunction userFunction = new UserFunction();
		userFunction.setUserFunctionId(userFunctionModel.getUserFunctionId());
		userFunction.setUserFunctionName(userFunctionModel.getUserFunctionName());
		userFunction.setDescription(userFunctionModel.getDescription());
		
		UserFunction userFunctionAfterSave = userFunctionRepository.save(userFunction);
		
		if(userFunctionAfterSave != null) {
			return "Thêm quyền hạn thành công";
		}else {
			return "Thêm quyền hạn thất bại";
		}
	}
	
	public String editFunction(UserFunctionModel userFunctionModel){
		Boolean isExist = userFunctionRepository.existsById(userFunctionModel.getUserFunctionId());
		if(isExist) {
			UserFunction userFunction = new UserFunction();
			userFunction.setUserFunctionId(userFunctionModel.getUserFunctionId());
			userFunction.setUserFunctionName(userFunctionModel.getUserFunctionName());
			userFunction.setDescription(userFunctionModel.getDescription());
			
			UserFunction userFunctionAfterSave = userFunctionRepository.save(userFunction);
			
			if(userFunctionAfterSave != null) {
				return "Sửa quyền hạn thành công";
			}else {
				return "Sửa quyền hạn thất bại";
			}
		}else {
			return "Quyền hạn không tồn tại";
		}
	}

	public String deleteFunction(Long userFunctionId){
		userFunctionRepository.deleteById(userFunctionId);
		return "Xóa chức danh thành công";
	}
}
