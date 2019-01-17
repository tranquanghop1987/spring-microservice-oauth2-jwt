package com.vnpt.webappraw.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vnpt.webappraw.model.CategoryModel;
import com.vnpt.webappraw.model.UserAddModel;
import com.vnpt.webappraw.model.UserFunctionModel;
import com.vnpt.webappraw.model.UserInfoResponseModel;
import com.vnpt.webappraw.model.UserSessionModel;
import com.vnpt.webappraw.service.CategoryService;
import com.vnpt.webappraw.service.RoleService;

@Controller
@RequestMapping(value="/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getListCategory(
			HttpSession session
			,Model model
			,@RequestParam(value="keyword", required=false) String keyword
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			List<CategoryModel> listCategory = categoryService.searchCategory(keyword != null ? keyword : "", session);
			List<UserFunctionModel> listAllUserFunction = roleService.getAllFunction(session);
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("listAllUserFunction", listAllUserFunction);
			model.addAttribute("categoryModel", new CategoryModel());
			return "pages/category/category";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/{categoryId}", method=RequestMethod.GET)
	public @ResponseBody CategoryModel userInfo(
			HttpSession session
			,Model model
			,@PathVariable("categoryId") Long categoryId
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			CategoryModel categoryModel = categoryService.getCategoryDetail(categoryId, session);
			return categoryModel;
		}else {
			return null;
		}
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addUser(
			HttpSession session
			,Model model
			,CategoryModel categoryModel
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = categoryService.addCategory(categoryModel, session);
			return "redirect:/category/list";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editUser(
			HttpSession session
			,Model model
			,CategoryModel categoryModel
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = categoryService.editCategory(categoryModel, session);
			return "redirect:/category/list";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/delete/{categoryId}", method=RequestMethod.POST)
	public String deleteCategory(
			HttpSession session
			,Model model
			,@PathVariable("categoryId") Long categoryId
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = categoryService.deleteCategory(categoryId, session);
			return "redirect:/category/list";
		}else {
			return "redirect:/login";
		}
	}
}
