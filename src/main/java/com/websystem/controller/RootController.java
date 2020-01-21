package com.websystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystem.constant.WebsystemConst.SessionConst;
import com.websystem.db.noteRepository;
import com.websystem.entity.db.NoteEntity;

/**
 * トップコントローラ
 */
@Controller
public class RootController {
  @Autowired
  private noteRepository noteRepo;

  /**
   * トップ画面用コントローラ
   * 
   * @param model
   * @param req
   * @return
   */
  @RequestMapping(value="/",method=RequestMethod.GET)
  public String rootController(Model model, HttpServletRequest req) {
    HttpSession session = req.getSession();
    String id = (String)session.getAttribute(SessionConst.ID);
    // ログインチェック
    if (id == null) {
      return "redirect:login";
    }

    // 投稿されているブログを確認
    List<NoteEntity> items = noteRepo.findAll();
    model.addAttribute("items", items);
    model.addAttribute("lineConnect", session.getAttribute(SessionConst.LINE_CONNECT));

    return "top";
  }
}
