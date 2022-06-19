package com.crm.ssm.controller;

/**
 * ÓÃ»§
 * 
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.ssm.entity.SysUser;
import com.crm.ssm.service.UserService;

@Controller
public class LogController {
	
	@Autowired
	private UserService userService;
	
	//µÇÂ¼
	@PostMapping("/login")
	public String login(String name,String pwd,HttpSession session) {
		SysUser sysUser = userService.login(name, pwd);
		if (sysUser!=null) {
			session.setAttribute("user", sysUser);
			return "main";
		}else {
			return "login";
		}
	}
	
	//µÇ³ö
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
}
