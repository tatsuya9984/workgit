package com.websystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.websystem.constant.WebsystemConst.SessionConst;
import com.websystem.db.AuthRepository;
import com.websystem.entity.db.AuthEntity;

/**
 * ログインコントローラ
 */
@Controller
@RequestMapping("/login")
public class LoginController {
  @Autowired
  private AuthRepository authRepo;

  /**
   * ログイン画面出力コントローラ
   * 
   * @param model
   * @param req
   * @return
   */
  @RequestMapping(method=RequestMethod.GET)
  public String getLogin(Model model, HttpServletRequest req) {
    model.addAttribute("errorMsg", "");
    return "login";
  }

  /**
   * 認証受付コントローラ
   * 
   * @param model
   * @param req
   * @param id ユーザID
   * @param pswd パスワード
   * @return
   */
  @RequestMapping(method=RequestMethod.POST)
  public String postLogin(Model model, HttpServletRequest req,
      @RequestParam(name = "id", required = true) String id,
      @RequestParam(name = "password", required = true) String pswd) {
    HttpSession session = req.getSession();
    AuthEntity auth = authRepo.findByUserIdIs(id);

    // 認証情報の確認
    if(auth != null && pswd.equals(auth.getPassword())) {
      // 認証情報が正しければログイン済みにする
      session.setAttribute(SessionConst.ID, id);
      if(auth.getLineId() != null) {
        // 認証情報にLINE_IDが存在すれば連携フラグをON
        session.setAttribute(SessionConst.LINE_CONNECT, true);
      } else {
        // 認証情報にLINE_IDが存在しなければ連携フラグをOFF
        session.setAttribute(SessionConst.LINE_CONNECT, false);
      }
    } else {
      // 認証情報が確認できない場合はエラーメッセージをモデルに入れてログイン画面へ
      model.addAttribute("errorMsg", "IDまたはパスワードが違います");
      return "login";
    }

    return "redirect:";
  }
}
