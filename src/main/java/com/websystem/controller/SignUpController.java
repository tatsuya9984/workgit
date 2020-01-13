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
import com.websystem.db.UserRepository;
import com.websystem.entity.db.AuthEntity;
import com.websystem.entity.db.UserEntity;
import com.websystem.service.MailService;

@Controller
public class SignUpController {
  @Autowired
  private AuthRepository authRepo;
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private MailService mailService;
 
  @GetMapping("/signup")
  public String getSignUp(Model model, HttpServletRequest req) {
    return "signup";
  }
 
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
      sendableFlag = false;
    }
    
    try {
      if (!password.equals(rePassword)) {
        throw new Exception("パスワードが一致しませんでした");
      }

      if (authRepo.findByUserIdIs(userId) != null) {
        throw new Exception("このユーザーIDはすでに利用されています");
      }
      AuthEntity auth = new AuthEntity();
      auth.setUserId(userId);
      auth.setPassword(password);
      authRepo.saveAndFlush(auth);

      UserEntity user = new UserEntity();
      user.setUserId(userId);
      user.setFamilyName(familyName);
      user.setGivenName(givenName);
      user.setMailAddress(mailAddress);
      user.setSendableFlag(sendableFlag);
      userRepo.saveAndFlush(user);
    } catch (Exception e) {
      model.addAttribute("errorMsg", e.getMessage());
      model.addAttribute("user_id", userId);
      model.addAttribute("fimily_name", familyName);
      model.addAttribute("given_name", givenName);
      model.addAttribute("mail_address", mailAddress);
      model.addAttribute("sendable_flag", sendableFlag);
      return "signup";
    }
    HttpSession session = req.getSession();
    session.setAttribute("id", userId);
    if (sendableFlag) {
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

