package com.websystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController {
  @RequestMapping(method=RequestMethod.GET)
  public String logout(Model model, HttpServletRequest req) {
    HttpSession session = req.getSession();
    session.invalidate();
    return "redirect:login";
  }
}
