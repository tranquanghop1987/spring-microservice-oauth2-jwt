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
import com.vnpt.webappraw.model.UserSessionModel;
import com.vnpt.webappraw.service.FunctionService;

@Controller
@RequestMapping("/function")
public class FunctionController {
	
	@Autowired
	FunctionService functionService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getListCategory(
			HttpSession session
			,Model model
			,@RequestParam(value="keyword", required=false) String keyword
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			List<UserFunctionModel> listUserFunctionModel = functionService.searchFunction(keyword != null ? keyword : "", session);
			model.addAttribute("listUserFunction", listUserFunctionModel);
			model.addAttribute("userFunctionModel", new UserFunctionModel());
			return "pages/function/function";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/{userFunctionId}", method=RequestMethod.GET)
	public @ResponseBody UserFunctionModel getFunctionInfo(
			HttpSession session
			,Model model
			,@PathVariable("userFunctionId") Long userFunctionId
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			UserFunctionModel userFunctionModel = functionService.getFunctionInfo(userFunctionId, session);
			return userFunctionModel;
		}else {
			return null;
		}
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addFunction(
			HttpSession session
			,Model model
			,UserFunctionModel userFunctionModel
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = functionService.addFunction(userFunctionModel, session);
			return "redirect:/function/list";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editFunction(
			HttpSession session
			,Model model
			,UserFunctionModel userFunctionModel
			) {
		UserSessionModel userSession = (UserSessionModel)session.getAttribute("userSession");
		if(userSession != null) {
			String s = functionService.editFunction(userFunctionModel, session);
			return "redirect:/function/list";
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
			String s = functionService.deleteFunction(userFunctionId, session);
			return "redirect:/function/list";
		}else {
			return "redirect:/login";
		}
	}
}
