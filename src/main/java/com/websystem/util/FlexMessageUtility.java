package com.websystem.util;

import java.util.List;

import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectMode;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectRatio;
import com.linecorp.bot.model.message.flex.component.Image.ImageSize;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Carousel;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import com.websystem.constant.WebsystemConst.AlterMessage;
import com.websystem.constant.WebsystemConst.ColorConst;

/**
 * LINEフレックスメッセージ作成ユーティリティクラス
 */
@Component
public class FlexMessageUtility {

  /**
   * 画像作成
   * 
   * @param url 画像のurl
   * @return 画像
   */
  public Image createImage(String url) {
    return Image.builder()
        .url(url)
        .size(ImageSize.MD)
        .aspectRatio(ImageAspectRatio.R1TO1)
        .aspectMode(ImageAspectMode.Cover)
        .build();
  }

  /**
   * アクション付き画像の作成
   * 
   * @param url 画像のurl
   * @param actionURL タップした時のリンク
   * @return アクション付き画像
   */
  public Image createImage(String url, String actionURL) {
    return Image.builder()
        .url(url)
        .size(ImageSize.MD)
        .aspectRatio(ImageAspectRatio.R1TO1)
        .aspectMode(ImageAspectMode.Cover)
        .action(new URIAction("jump link", actionURL))
        .build();
  }

  /**
   * サイズとアスペクト比を指定したアクション付き画像の作成
   * 
   * @param url 画像のパス
   * @param actionURL 画像をタップした時のリンク
   * @param size 画像サイズ
   * @param aspectRatio 画像のアスペクト比
   * @return アクション付き画像
   */
  public Image createImage(String url, String actionURL, ImageSize size, ImageAspectRatio aspectRatio) {
    return Image.builder()
        .url(url)
        .size(size)
        .aspectRatio(aspectRatio)
        .aspectMode(ImageAspectMode.Cover)
        .action(new URIAction("jump link", actionURL))
        .build();
  }

  /**
   * タイトル付きテキストボックスの作成
   * 
   * @param title タイトル
   * @param text 内容
   * @return タイトル付きテキストボックス
   */
  public Box createTextBox(String title, String text) {
    return Box.builder()
        .layout(FlexLayout.BASELINE)
        .margin(FlexMarginSize.MD)
        .contents(asList(textBuilder(title, ColorConst.BLACK), textBuilder(text)))
        .build();
  }

  /**
   * テキストボックスの作成
   * 
   * @param text 内容
   * @return テキストボックス
   */
  public Box createTextBox(String text) {
    return Box.builder()
        .layout(FlexLayout.BASELINE)
        .margin(FlexMarginSize.MD)
        .contents(asList(textBuilder(text)))
        .build();
  }

  /**
   * ヘッダー用テキストボックスの作成
   * 
   * @param text 内容
   * @return ヘッダー用テキストボックス
   */
  public Box createHeaderTextBox(String text) {
    return Box.builder()
        .layout(FlexLayout.BASELINE)
        .margin(FlexMarginSize.MD)
        .contents(asList(textBuilder(text, ColorConst.BLACK, FlexFontSize.LG)))
        .build();
  }

  /**
   * フッター用テキストボックスの作成
   * 
   * @param text 内容
   * @return フッター用テキストボックス
   */
  public Box createFooterTextaBox(String text) {
    return Box.builder()
        .layout(FlexLayout.BASELINE)
        .margin(FlexMarginSize.MD)
        .contents(asList(textBuilder(text, ColorConst.DEFAULT_COLOR, FlexFontSize.XXS)))
        .build();
  }

  /**
   * アクション付きフッター用テキストボックスの作成
   * 
   * @param text 内容
   * @param actionURL タップした時のリンク
   * @return アクション付きフッター用テキストボックス
   */
  public Box createFooterTextaBox(String text, String actionURL) {
    return Box.builder()
        .layout(FlexLayout.BASELINE)
        .margin(FlexMarginSize.MD)
        .contents(asList(textWithActionBuilder(text, ColorConst.DEFAULT_COLOR, FlexFontSize.XXS, actionURL)))
        .build();
  }

  /**
   * デフォルトカラーのテキスト取得
   * 
   * @param text 内容
   * @return Textインスタンス
   */
  private Text textBuilder(String text) {
    return textBuilder(text, ColorConst.DEFAULT_COLOR, FlexFontSize.SM);
  }

  /**
   * 指定したカラーのテキスト取得
   * 
   * @param text 内容
   * @param colorCode カラーコード(#〇〇〇〇〇〇)
   * @return Textインスタンス
   */
  private Text textBuilder(String text, String colorCode) {
    return textBuilder(text, colorCode, FlexFontSize.SM);
  }

  /**
   * 指定したカラーと指定したサイズのテキスト取得
   * 
   * @param text 内容
   * @param colorCode カラーコード(#〇〇〇〇〇〇)
   * @param size サイズ(FlexFontSizeを参照)
   * @return Textインスタンス
   */
  private Text textBuilder(String text, String colorCode, FlexFontSize size) {
    return Text.builder()
        .text(text)
        .size(size)
        .color(colorCode)
        .margin(FlexMarginSize.MD)
        .flex(0)
        .build();
  }

  /**
   * 指定したカラーと指定したサイズのアクション付きテキスト取得
   * 
   * @param text 内容
   * @param colorCode カラーコード(#〇〇〇〇〇〇)
   * @param size サイズ(FlexFontSizeを参照)
   * @param actionURL タップした時のリンク
   * @return Textインスタンス
   */
  private Text textWithActionBuilder(String text, String colorCode, FlexFontSize size, String actionURL) {
    return Text.builder()
        .text(text)
        .size(size)
        .color(colorCode)
        .action(new URIAction("jump link", actionURL))
        .margin(FlexMarginSize.MD)
        .flex(0)
        .build();
  }

  /**
   * Bubbleの取得
   * 
   * @param header ヘッダー
   * @param heroUrl ヒーロー画像URL
   * @param body 本題
   * @param footer フッター
   * @return BubbleFlexメッセージ
   */
  public FlexMessage getForBubble(List<FlexComponent> header, String heroUrl, List<FlexComponent> body, List<FlexComponent> footer) {
    Bubble bubble = createBubble(header, heroUrl, body, footer);
    return new FlexMessage(AlterMessage.FLEX_MESSAGE, bubble);
  }

  /**
   * ヒーローアクション付きBubbleの取得
   * 
   * @param header ヘッダー
   * @param heroUrl ヒーロー画像URL
   * @param actionURL ヒーロー画像をタップした時のリンク
   * @param body 本題
   * @param footer フッター
   * @return ヒーローアクション付きBubbleFlexメッセージ
   */
  public FlexMessage getForBubbleWithHeroAction(List<FlexComponent> header, String heroUrl, String actionURL, List<FlexComponent> body, List<FlexComponent> footer) {
    Bubble bubble = createBubbleWithHeroAction(header, heroUrl, actionURL, body, footer);
    return new FlexMessage(AlterMessage.FLEX_MESSAGE, bubble);
  }

  /**
   * Bubbleの作成
   * 
   * @param header ヘッダー
   * @param heroUrl ヒーロー画像URL
   * @param body 本題
   * @param footer フッター
   * @return Bubble
   */
  public Bubble createBubble(List<FlexComponent> header, String heroUrl, List<FlexComponent> body, List<FlexComponent> footer) {
    Box headerBox = createVerticalBlock(header);
    Image heroBox = createImage(heroUrl);
    Box bodyBox = createVerticalBlock(body);
    Box footerBox = createVerticalBlock(footer);
    return Bubble.builder()
        .header(headerBox)
        .hero(heroBox)
        .body(bodyBox)
        .footer(footerBox)
        .build();
  }

  /**
   * ヒーローアクション付きBubbleの作成
   * 
   * @param header ヘッダー
   * @param heroUrl ヒーロー画像URL
   * @param actionURL ヒーロー画像をタップした時のリンク
   * @param body 本題
   * @param footer フッター
   * @return ヒーローアクション付きBubble
   */
  public Bubble createBubbleWithHeroAction(List<FlexComponent> header, String heroUrl, String actionURL, List<FlexComponent> body, List<FlexComponent> footer) {
    Box headerBox = createVerticalBlock(header);
    Image heroBox = createImage(heroUrl, actionURL, ImageSize.FULL_WIDTH ,ImageAspectRatio.R16TO9);
    Box bodyBox = createVerticalBlock(body);
    Box footerBox = createVerticalBlock(footer);
    return Bubble.builder()
        .header(headerBox)
        .hero(heroBox)
        .body(bodyBox)
        .footer(footerBox)
        .build();
  }

  /**
   * カルーセルFlexメッセージの取得
   * 
   * @param bubbleList bubbleのリスト
   * @return カルーセル
   */
  public FlexMessage getForCarousel(List<Bubble> bubbleList) {
    Carousel carousel = Carousel.builder()
        .contents(bubbleList)
        .build();
    return new FlexMessage(AlterMessage.FLEX_MESSAGE, carousel);
  }

  /**
   * コンテンツ縦配置ボックスの作成
   * 
   * @param flexComponentList コンテンツ(FlexComponent)のリスト
   * @return コンテンツ縦配置ボックス
   */
  private Box createVerticalBlock(List<FlexComponent> flexComponentList) {
    return Box.builder()
        .layout(FlexLayout.VERTICAL)
        .contents(flexComponentList)
        .build();
  }
}
