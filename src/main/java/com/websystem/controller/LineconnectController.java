package com.websystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lineconnect")
public class LineconnectController {
  @RequestMapping(method=RequestMethod.GET)
  public String connect() {
    
    return "login";
   }
}
