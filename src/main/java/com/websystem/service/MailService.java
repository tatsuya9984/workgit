package com.websystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {
  @Autowired
  private MailSender sender;

  public void sendMail(String to, String title, String body) {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setFrom("fassion.service@jp.service");
    msg.setTo(to);
    msg.setSubject(title); //タイトルの設定
    msg.setText(body); //本文の設定
    this.sender.send(msg);
  }

  public Body createBuilder() {
    return new Body();
  }

  public class Body {
    StringBuilder sb;
    
    private Body() {
      sb = new StringBuilder();
    }
    
    public Body addNewLine(String line) {
      sb.append(line + "\n");
      return this;
    }
    
    @Override
    public String toString() {
      return sb.toString();
    }
  }
}
