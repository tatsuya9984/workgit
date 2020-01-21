package com.websystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ログアウトコントローラ
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
  /**
   * ログアウト処理受付コントローラ
   * 
   * @param model
   * @param req
   * @return
   */
  @RequestMapping(method=RequestMethod.GET)
  public String logout(Model model, HttpServletRequest req) {
    HttpSession session = req.getSession();
    // セッション破棄
    session.invalidate();
    return "redirect:login";
  }
}
