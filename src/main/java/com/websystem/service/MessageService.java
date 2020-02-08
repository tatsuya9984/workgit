package com.websystem.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.response.BotApiResponse;
import com.websystem.util.FlexMessageUtility;

/**
 * ラインメッセージのハンドルサービス
 */
@Service
public class MessageService {
  @Autowired
  private LineMessagingClient lineMessagingClient;
  @Autowired
  private FlexMessageUtility flexMessageUtility;

  /**
   * 挨拶文をプッシュ
   * 
   * @param lineId メッセージ送信先LINE_ID
   */
  public void pushHelloMessage(String lineId) {
    List<FlexComponent> header = flexMessageUtility.createBuilder()
        .add(flexMessageUtility.createHeaderTextBox("【Fassion Talk】とは"))
        .getFlexBoxList();
    String heroURL = "https://www.shibaura1101.com/fassion/topback.png";
    String actionURL = "https://fassion-talk.herokuapp.com";
    List<FlexComponent> body = flexMessageUtility.createBuilder()
        .add(flexMessageUtility.createTextBox("今流行りの服がわからない？"))
        .add(flexMessageUtility.createTextBox("今すぐみんなが着ている服をチェックしよう"))
        .getFlexBoxList();
    List<FlexComponent> footer = flexMessageUtility.createBuilder()
        .add(flexMessageUtility.createFooterTextaBox("fassion-talk"))
        .getFlexBoxList();
    try {
      BotApiResponse res = lineMessagingClient
        .pushMessage(new PushMessage(lineId, flexMessageUtility.getForBubbleWithHeroAction(header, heroURL, actionURL, body, footer))).get();
      System.out.println(res.getMessage());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException();
    }
  }

  /**
   * ログイン完了メッセージをプッシュ
   * 
   * @param lineId メッセージ送信先LINE_ID
   */
  public void pushLoginMessage(String lineId) {
    try {
      BotApiResponse res = lineMessagingClient
        .pushMessage(new PushMessage(lineId, new TextMessage("ログインされました"))).get();
      System.out.println(res.getMessage());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException();
    }
  }
}