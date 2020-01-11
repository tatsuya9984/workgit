package com.websystem.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



public class SignController {
	private Map<String,String> radioSex;
	 
	private Map<String,String> initRadioSex(){
		Map<String,String> radio = new LinkedHashMap<>();
		radio.put("女性", "true");
		radio.put("男性", "false");
 
		return radio;
	}
 
	@GetMapping("/signup")
	public String getSignUp(Model model) {
		radioSex = initRadioSex();
		model.addAttribute("radioSex",radioSex);
 
		return "login/signup";
	}
 
	@PostMapping("/signup")
	public String postSignUp(Model model) {
		return "redirect:/login";
	}
}

