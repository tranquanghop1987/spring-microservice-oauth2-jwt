package com.vnpt.webappraw.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vnpt.webappraw.common.CategoryMenu;
import com.vnpt.webappraw.model.AuthModel;
import com.vnpt.webappraw.model.CategoryItem;
import com.vnpt.webappraw.model.CategoryModel;
import com.vnpt.webappraw.model.UserInfoResponseModel;
import com.vnpt.webappraw.model.UserSessionModel;
import com.vnpt.webappraw.service.CategoryService;
import com.vnpt.webappraw.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/")
	public String index() {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			return "index";
		}
		return "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping(value="/loginProccess", method = RequestMethod.POST)
	public String loginProcess(
			@RequestParam(value="username", required=true)String username
			,@RequestParam(value="password", required=true)String password
			,Model model
			) {
		AuthModel authModel = userService.getToken(username, password);
		if(authModel != null) {
			String accessToken = authModel.getAccessToken();
			UserInfoResponseModel userInfo = userService.getUserInfo(accessToken);
			UserSessionModel userSession = new UserSessionModel();
			userSession.setUsername(userInfo.getUsername());
			userSession.setEmail(userInfo.getEmail());
			userSession.setRole(userInfo.getRole());
			userSession.setListUserFunction(userInfo.getListUserFunction());
			userSession.setAccessToken(accessToken);
			session.setAttribute("userSession", userSession);
			
			List<CategoryModel> listCategoryModel = categoryService.searchCategory("", session);
			CategoryMenu menu = new CategoryMenu();
			List<CategoryItem> listMenuItem = menu.getCategoryMenu(listCategoryModel);
			session.setAttribute("listMenuItem", listMenuItem);
			return "redirect:/";
		}else {
			return "redirect:/login";
		}
	}
}
