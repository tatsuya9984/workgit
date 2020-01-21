package com.websystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * メールサービスクラス
 */
@Service
public class MailService {
  @Autowired
  private MailSender sender;

  /**
   * メール送信
   * @param to 宛先メールアドレス
   * @param title サブジェクト
   * @param body 本文
   */
  public void sendMail(String to, String title, String body) {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setFrom("fassion.service@jp.service");
    msg.setTo(to);
    msg.setSubject(title); //タイトルの設定
    msg.setText(body); //本文の設定
    this.sender.send(msg);
  }

  /**
   * 本文ビルダーの作成
   * @return
   */
  public Body createBuilder() {
    return new Body();
  }

  /**
   * 本文格納クラス
   * 使い方：コンストラクタでインスタンスを生成し、addNewLineで本文を追加→toStringで文字列を取得
   */
  public class Body {
    StringBuilder sb;

    /**
     * コンストラクタ
     * 機能：本文作成用ビルダーの生成
     */
    private Body() {
      sb = new StringBuilder();
    }

    /**
     * 本文に一行追加する
     * 
     * @param line 記述したい一行分文字列(改行文字なし)
     * @return 本文格納インスタンス
     */
    public Body addNewLine(String line) {
      sb.append(line + "\n");
      return this;
    }

    /**
     * 格納された本文を文字列に変換する
     */
    @Override
    public String toString() {
      return sb.toString();
    }
  }
}
