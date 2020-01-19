package com.websystem.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.websystem.service.LineService;

@Controller
@RequestMapping("/lineconnect")
public class LineconnectController {
  @Autowired
  private LineService lineService;

  @RequestMapping(value="/callback", method=RequestMethod.GET)
  public String connect(
      @RequestParam(name = "code", required = true) String code) {
    System.out.println(code);
    return "top";
   }
}
