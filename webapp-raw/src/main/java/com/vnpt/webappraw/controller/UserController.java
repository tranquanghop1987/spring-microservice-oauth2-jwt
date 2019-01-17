package com.vnpt.webappraw.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vnpt.webappraw.model.UserAddModel;
import com.vnpt.webappraw.model.UserInfoResponseModel;
import com.vnpt.webappraw.model.UserListResponseModel;
import com.vnpt.webappraw.model.UserRoleModel;
import com.vnpt.webappraw.model.UserSessionModel;
import com.vnpt.webappraw.service.RoleService;
import com.vnpt.webappraw.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	RoleService roleService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String userList(
			HttpSession session
			,Model model
			,@RequestParam(value="keyword", required=false) String keyword
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			List<UserInfoResponseModel> listUser = userservice.searchListUser(keyword, session);
			List<UserRoleModel> listAllRoles = roleService.getAllRoles(session);
			model.addAttribute("listUser", listUser);
			model.addAttribute("listAllRoles", listAllRoles);
			model.addAttribute("userAddModel", new UserAddModel());
			return "pages/user/user";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addUser(
			HttpSession session
			,Model model
			,UserAddModel userAddModel
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = userservice.addUser(userAddModel, session);
			return "redirect:/user/list";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editUser(
			HttpSession session
			,Model model
			,UserAddModel userAddModel
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = userservice.editUser(userAddModel, session);
			return "redirect:/user/list";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/delete/{username}", method=RequestMethod.POST)
	public String addUser(
			HttpSession session
			,Model model
			,@PathVariable("username") String username
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = userservice.deleteUser(username, session);
			return "redirect:/user/list";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public @ResponseBody UserInfoResponseModel userInfo(
			HttpSession session
			,Model model
			,@PathVariable("username") String username
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			UserInfoResponseModel userInfoResponseModel = userservice.findByUsername(username, session);
			return userInfoResponseModel;
		}else {
			return null;
		}
	}
}
