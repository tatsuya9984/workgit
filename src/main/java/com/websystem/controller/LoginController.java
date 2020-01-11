package com.websystem.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.websystem.entity.UserEntity;
import com.websystem.entity.WebsystemRepository;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private WebsystemRepository websystem;
	
	//@GetMapping(value="/")後で調べる
	@RequestMapping(method=RequestMethod.GET)
	public String getLogin(Model model, HttpServletRequest req) {
		model.addAttribute("errorMsg", "");
		return "login";
	}
	
	//@PostMapping(value="/login",POST)
	@RequestMapping(method=RequestMethod.POST)
	public String postLogin(Model model, HttpServletRequest req,
			@RequestParam(name = "Account_ID", required = true) String id,
			@RequestParam(name = "password", required = true) String pswd) {
		HttpSession session = req.getSession();
		System.out.println(pswd);
		
		UserEntity user = websystem.findById(id);
		
		if(user != null && pswd.equals(user.getPassword())) {
			session.setAttribute("id", id);
		} else {
			model.addAttribute("errorMsg", "IDまたはパスワードが違います");
			return "login";
		}
		return "redirect:top";
	}
}
