package com.websystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websystem.db.AuthRepository;
import com.websystem.db.UsersRepository;
import com.websystem.entity.db.AuthEntity;
import com.websystem.entity.db.UsersEntity;
import com.websystem.service.MailService;

/**
 * Sign Upコントローラ
 */
@Controller
public class SignUpController {
  @Autowired
  private AuthRepository authRepo;
  @Autowired
  private UsersRepository userRepo;
  @Autowired
  private MailService mailService;

  /**
   * 新規ユーザ登録画面用コントローラ
   * 
   * @param model
   * @param req
   * @return
   */
  @GetMapping("/signup")
  public String getSignUp(Model model, HttpServletRequest req) {
    return "signup";
  }

  /**
   * 新規ユーザ登録処理コントローラ
   * 
   * @param model
   * @param req
   * @param userId ログイン用ユーザID
   * @param password パスワード
   * @param rePassword パスワード確認用
   * @param familyName 姓
   * @param givenName 名
   * @param mailAddress 通知用メールアドレス
   * @param sendableFlag メール配信フラグ
   * @return
   */
  @PostMapping("/signup")
  public String postSignUp(Model model, HttpServletRequest req,
      @RequestParam(name = "user_id", required = true) String userId,
      @RequestParam(name = "password", required = true) String password,
      @RequestParam(name = "re_password", required = true) String rePassword,
      @RequestParam(name = "family_name", required = true) String familyName,
      @RequestParam(name = "given_name", required = true) String givenName,
      @RequestParam(name = "mail_address", required = true) String mailAddress,
      @RequestParam(name = "sendable_flag", required = false) boolean sendableFlag) {
    if (sendableFlag != true) {
      // メール配信可能でない場合はメール配信フラグをOFF
      sendableFlag = false;
    }

    try {
      if (!password.equals(rePassword)) {
        throw new Exception("パスワードが一致しませんでした");
      }

      if (authRepo.findByUserIdIs(userId) != null) {
        throw new Exception("このユーザーIDはすでに利用されています");
      }

      // 認証情報登録
      AuthEntity auth = new AuthEntity();
      auth.setUserId(userId);
      auth.setPassword(password);
      authRepo.saveAndFlush(auth);

      // ユーザ情報登録
      UsersEntity user = new UsersEntity();
      user.setUserId(userId);
      user.setFamilyName(familyName);
      user.setGivenName(givenName);
      user.setMailAddress(mailAddress);
      user.setSendableFlag(sendableFlag);
      userRepo.saveAndFlush(user);

    } catch (Exception e) {
      // デフォルト値用モデル作成
      model.addAttribute("errorMsg", e.getMessage());
      model.addAttribute("user_id", userId);
      model.addAttribute("fimily_name", familyName);
      model.addAttribute("given_name", givenName);
      model.addAttribute("mail_address", mailAddress);
      model.addAttribute("sendable_flag", sendableFlag);
      return "signup";
    }

    // 新規ユーザ登録後はログイン済みにする
    HttpSession session = req.getSession();
    session.setAttribute("id", userId);
    if (sendableFlag) {
      // メール配信フラグがONの場合はメール送信
      String body = mailService.createBuilder()
          .addNewLine("新規ユーザ登録完了")
          .addNewLine("")
          .addNewLine("----------ユーザ情報----------")
          .addNewLine("ユーザID：" + userId)
          .addNewLine("パスワード：" + password)
          .addNewLine("氏名：" + familyName + " " + givenName)
          .addNewLine("-------------------------------")
          .addNewLine("")
          .addNewLine("サービス利用は以下リンクより")
          .addNewLine("http://localhost:8080/")
          .toString();
      mailService.sendMail(mailAddress, "新規ユーザ登録完了のお知らせ", body);
    }

    return "redirect:";
  }
}

