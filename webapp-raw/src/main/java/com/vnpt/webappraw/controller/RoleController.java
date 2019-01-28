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
import com.vnpt.webappraw.model.UserFunctionModel;
import com.vnpt.webappraw.model.UserRoleModel;
import com.vnpt.webappraw.model.UserSessionModel;
import com.vnpt.webappraw.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String searchUserRoles(
			HttpSession session
			,Model model
			,@RequestParam(value="keyword", required=false) String keyword
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			List<UserRoleModel> listUserRoleModel = roleService.searchUserRoles(keyword != null ? keyword : "", session);
			model.addAttribute("listUserRoleModel", listUserRoleModel);
			return "pages/role/role";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/{userRoleId}", method=RequestMethod.GET)
	public @ResponseBody UserRoleModel getRoleInfo(
			HttpSession session
			,Model model
			,@PathVariable("userRoleId") Long userRoleId
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			UserRoleModel userRoleModel = roleService.getRoleInfo(userRoleId, session);
			return userRoleModel;
		}else {
			return null;
		}
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addRole(
			HttpSession session
			,Model model
			,UserRoleModel userRoleModel
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = roleService.addRole(userRoleModel, session);
			return "redirect:/role/list";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editRole(
			HttpSession session
			,Model model
			,UserRoleModel userRoleModel
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = roleService.editRole(userRoleModel, session);
			return "redirect:/role/list";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/delete/{userFunctionId}", method=RequestMethod.POST)
	public String deleteCategory(
			HttpSession session
			,Model model
			,@PathVariable("userFunctionId") Long userFunctionId
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = roleService.deleteRole(userFunctionId, session);
			return "redirect:/role/list";
		}else {
			return "redirect:/login";
		}
	}
}
