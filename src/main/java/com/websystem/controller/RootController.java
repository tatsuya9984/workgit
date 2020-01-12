package com.websystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String rootController(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		if (id == null) {
			return "redirect:login";
		}
		model.addAttribute("name", id);
		return "top";
	}
}
