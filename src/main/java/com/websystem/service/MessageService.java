package com.websystem.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

/**
 * ラインメッセージのハンドルサービス
 */
@Service
public class MessageService {
  @Autowired
  private LineMessagingClient lineMessagingClient;

  /**
   * 挨拶文をプッシュ
   * 
   * @param lineId メッセージ送信先LINE_ID
   */
  public void pushHelloMessage(String lineId) {
    try {
      BotApiResponse res = lineMessagingClient
        .pushMessage(new PushMessage(lineId, new TextMessage("ライン連携ありがとう！"))).get();
      System.out.println(res.getMessage());
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException();
    }
  }
}