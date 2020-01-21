package com.websystem.controller;

import java.io.IOException;

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
    System.out.println(code);
    TokenResponse tokenResponse = lineService.getToken(code);
    ProfileResponse profileResponse = lineService.getProfile(tokenResponse.getAccess_token());
    HttpSession session = req.getSession();
    AuthEntity auth = authRepo.findByUserIdIs((String)session.getAttribute("id"));
    auth.setLineId(profileResponse.getUserId());
    session.setAttribute("lineConnect", true);
    authRepo.saveAndFlush(auth);
    return "redirect:/";
   }
}
