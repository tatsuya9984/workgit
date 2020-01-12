package com.websystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystem.db.WebsystemRepository;
import com.websystem.entity.db.UserEntity;

@Controller
public class TopController {
	@Autowired
	private WebsystemRepository repo;
	
	@RequestMapping(value="/top",method=RequestMethod.GET)
	public String rootController(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		if (id == null) {
			return "redirect:/login";
		}
		UserEntity user = repo.findById(id);
		System.out.println("----------------------------------------");
		System.out.println("age:" + user.getAge());
		System.out.println("birth:" + user.getBrith());
		System.out.println("name:" + user.getUserName());
		System.out.println("id:" + user.getUserId());
		System.out.println("----------------------------------------");
		model.addAttribute("name", id);
		return "top";
	}
}
