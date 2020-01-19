package com.websystem.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystem.service.LineService;

@Controller
@RequestMapping("/lineconnect")
public class LineconnectController {
  @Autowired
  private LineService lineService;

  @RequestMapping(method=RequestMethod.GET)
  public String connect() {
    try {
      lineService.authorize();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "top";
   }
}
