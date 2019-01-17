package com.vnpt.category.controller;

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

import com.vnpt.category.model.CategoryModel;
import com.vnpt.category.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryModel>> listUser(@RequestParam(value="keyword", required=false)String keyword){
		List<CategoryModel> listCategory = categoryService.findCategoryByCategoryName(keyword != null ? keyword : "");
		return ResponseEntity.status(HttpStatus.OK).body(listCategory);
	}
	
	@RequestMapping(value="/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<CategoryModel> viewInfo(@PathVariable Long categoryId){
		CategoryModel userInfoModel = categoryService.getCategoryById(categoryId);
		return ResponseEntity.status(HttpStatus.OK).body(userInfoModel);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> addCategory(@RequestBody CategoryModel categoryModel){
		String returnMessage = categoryService.addCategory(categoryModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
	
	@RequestMapping(value="/delete/{categoryId}", method = RequestMethod.POST)
	public ResponseEntity<String>  deleteCategory(@PathVariable Long categoryId){
		String returnMessage = categoryService.deleteCategory(categoryId);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public ResponseEntity<String> editCategory(@RequestBody CategoryModel categoryModel){
		String returnMessage = categoryService.editCategory(categoryModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnMessage);
	}
}
