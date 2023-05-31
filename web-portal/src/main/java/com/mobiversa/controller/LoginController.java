package com.mobiversa.controller;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String showMyLoginPage() {
		System.out.println("LOGIN PAGE RETURNED");
		return "login";
	}


	@RequestMapping("/Home")
	public String showHome() {
		System.out.println("This is entry point of the portal");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		String role1 = null;
		for (GrantedAuthority authority : authorities) {
			String role = authority.getAuthority();
			role1 = role;
			System.out.println("ROLE :::" + role);
			// Do something with the role
		}
		System.out.println("ROLE :::" + role1);
		System.out.println("HOME PAGE RETURNED");
//		return "redirect:/payoutSummary/list";
		return "redirect:/payoutSummary/list";
	}
	@RequestMapping("/payoutList")
	public String payoutSummary() {
		System.out.println("REDIRECTED TO PAYOUT SUMMARY");
		return "payoutportal";
	}

	@RequestMapping("/admin")
	public String admin() {
		System.out.println("REDIRECTED TO PAYOUT SUMMARY");
		return "admin";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	@GetMapping("/forgotPassword")
	public String showForgotPasswordPage(final Model model, final Principal principal,HttpServletRequest request) {
		
		model.addAttribute("agentUserName",  principal.getName());
	    return "forgot-password";
	}
}
