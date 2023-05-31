package com.mobiversa.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mobiversa.service.ForgotPasswordService;

@Controller
public class ResetPasswordController {
	
	@Autowired
	ForgotPasswordService forgotPasswordService;
	
	@Transactional
	@RequestMapping(value = { "/agentProfdetails/confirmPasswordbyagent" }, method = RequestMethod.POST)
	public String confirmPassword(final Model model, final Principal principal, HttpServletRequest request) {
		
		System.out.println(principal.getName());
		System.out.println(request.getParameter("newPassword"));
		System.out.println(request.getParameter("password"));
		
		int num = forgotPasswordService.changeAgentPassWord(principal.getName(), request.getParameter("newPassword"), request.getParameter("password"));
		if(num==1) {
			return "login";
		}
		return "redirect:/logout";
	}
	

}