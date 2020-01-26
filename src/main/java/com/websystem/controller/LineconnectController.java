package com.websystem.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.websystem.constant.WebsystemConst.LineConst;
import com.websystem.constant.WebsystemConst.SessionConst;
import com.websystem.db.AuthRepository;
import com.websystem.entity.db.AuthEntity;
import com.websystem.entity.line.ProfileResponse;
import com.websystem.entity.line.TokenResponse;
import com.websystem.service.LineService;
import com.websystem.service.MessageService;

/**
 * ラインID連携コントローラ
 */
@Controller
@RequestMapping("/lineconnect")
public class LineconnectController {
  @Autowired
  private LineService lineService;
  @Autowired
  private AuthRepository authRepo;
  @Autowired
  private MessageService messageService;

  /**
   * 許可コード受付コントローラ
   * 機能：アクセストークン・プロファイル情報を取得する
   * 
   * @param req
   * @param code 許可コード
   * @return
   */
  @RequestMapping(value="/callback", method=RequestMethod.GET)
  public String connect(HttpServletRequest req,
      @RequestParam(name = "code", required = true) String code) {
    // ログインチェック
    HttpSession session = req.getSession();
    String id = (String)session.getAttribute(SessionConst.ID);
    if (id == null) {
      return "redirect:/login";
    }

    // アクセストークンの取得
    TokenResponse tokenResponse = lineService.getToken(code, LineConst.CALLBACK);
    // プロファイルの取得
    ProfileResponse profileResponse = lineService.getProfile(tokenResponse.getAccess_token());

    // LineIDを権限情報に設定
    AuthEntity auth = authRepo.findByUserIdIs(id);
    auth.setLineId(profileResponse.getUserId());
    session.setAttribute(SessionConst.LINE_CONNECT, true);
    authRepo.saveAndFlush(auth);

    // トップ画面へリダイレクト
    return "redirect:/";
  }

  /**
   * LINEログイン受付コントローラ
   * 
   * @param req
   * @param code 許可コード
   * @return
   */
  @RequestMapping(value="/login", method=RequestMethod.GET)
  public String login(HttpServletRequest req,
      @RequestParam(name = "code", required = true) String code) {
    // アクセストークンの取得
    TokenResponse tokenResponse = lineService.getToken(code, LineConst.LOGIN);
    // プロファイル情報の取得
    ProfileResponse profileResponse = lineService.getProfile(tokenResponse.getAccess_token());

    // LINE_IDから認証情報を取得
    AuthEntity auth = authRepo.findByLineIdIs(profileResponse.getUserId());
    if (auth == null) {
      return "redirect:/login";
    }
    // LINE_IDに対応する認証情報があればログイン済みにする
    HttpSession session = req.getSession();
    session.setAttribute(SessionConst.ID, auth.getUserId());
    session.setAttribute(SessionConst.LINE_CONNECT, true);

    // 挨拶文をLINEへプッシュ
    messageService.pushHelloMessage(auth.getLineId());

    return "redirect:/";
  }

  /**
   * ラインID連携の無効化
   * 
   * @param req
   * @return
   */
  @RequestMapping(value="/invalidate", method=RequestMethod.GET)
  public String login(HttpServletRequest req) {
    HttpSession session = req.getSession();
    String id = (String)session.getAttribute(SessionConst.ID);
    // ログインチェック
    if (id == null) {
      return "redirect:/login";
    }

    // ログイン済みの場合、ログインユーザIDに紐づくLINE_IDを削除
    AuthEntity auth = authRepo.findByUserIdIs(id);
    auth.setLineId(null);
    authRepo.saveAndFlush(auth);
    session.setAttribute(SessionConst.LINE_CONNECT, false);

    return "redirect:/";
  }
}
