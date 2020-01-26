package com.websystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

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
    lineMessagingClient
      .pushMessage(new PushMessage(lineId, new TextMessage("ライン連携ありがとう！")));
  }
}