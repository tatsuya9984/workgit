package com.websystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.websystem.db.AuthRepository;
import com.websystem.entity.db.AuthEntity;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private AuthRepository authRepo;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLogin(Model model, HttpServletRequest req) {
		model.addAttribute("errorMsg", "");
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postLogin(Model model, HttpServletRequest req,
			@RequestParam(name = "id", required = true) String id,
			@RequestParam(name = "password", required = true) String pswd) {
		HttpSession session = req.getSession();
		AuthEntity auth = authRepo.findByUserIdIs(id);
		
		if(auth != null && pswd.equals(auth.getPassword())) {
			session.setAttribute("id", id);
		} else {
			model.addAttribute("errorMsg", "IDまたはパスワードが違います");
			return "login";
		}
		return "redirect:";
	}
}
