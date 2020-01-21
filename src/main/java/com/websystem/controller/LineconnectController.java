package com.websystem.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.websystem.db.AuthRepository;
import com.websystem.entity.db.AuthEntity;
import com.websystem.entity.line.ProfileResponse;
import com.websystem.entity.line.TokenResponse;
import com.websystem.service.LineService;

@Controller
@RequestMapping("/lineconnect")
public class LineconnectController {
  @Autowired
  private LineService lineService;
  @Autowired
  private AuthRepository authRepo;

  @RequestMapping(value="/callback", method=RequestMethod.GET)
  public String connect(HttpServletRequest req,
      @RequestParam(name = "code", required = true) String code) {
    TokenResponse tokenResponse = lineService.getToken(code, "callback");
    ProfileResponse profileResponse = lineService.getProfile(tokenResponse.getAccess_token());
    HttpSession session = req.getSession();
    AuthEntity auth = authRepo.findByUserIdIs((String)session.getAttribute("id"));
    auth.setLineId(profileResponse.getUserId());
    session.setAttribute("lineConnect", true);
    authRepo.saveAndFlush(auth);
    return "redirect:/";
   }

  @RequestMapping(value="/login", method=RequestMethod.GET)
  public String login(HttpServletRequest req,
      @RequestParam(name = "code", required = true) String code) {
    System.out.println("getToken");
    TokenResponse tokenResponse = lineService.getToken(code, "login");
    System.out.println("getProfile");
    ProfileResponse profileResponse = lineService.getProfile(tokenResponse.getAccess_token());
    AuthEntity auth = authRepo.findByLineIdIs(profileResponse.getUserId());
    if (auth == null) {
      return "redirect:/login";
    }
    HttpSession session = req.getSession();
    session.setAttribute("id", auth.getUserId());
    session.setAttribute("lineConnect", true);
    return "redirect:/";
  }

  @RequestMapping(value="/invalidate", method=RequestMethod.GET)
  public String login(HttpServletRequest req) {
    HttpSession session = req.getSession();
    AuthEntity auth = authRepo.findByUserIdIs((String)session.getAttribute("id"));
    auth.setLineId(null);
    authRepo.saveAndFlush(auth);
    session.setAttribute("lineConnect", false);
    return "redirect:/";
  }
}
