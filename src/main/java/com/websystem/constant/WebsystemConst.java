package com.websystem.constant;

/**
 * 定数定義クラス
 */
public class WebsystemConst {
  /**
   * LINE用定数定義クラス
   */
  public static class LineConst {
    /** コールバックURL用 */
    public static final String CALLBACK = "callback";
    /** ログインURL用 */
    public static final String LOGIN = "login";
  }

  /**
   * SESSION用定数定義クラス
   */
  public static class SessionConst {
    /** ラインID連携フラグ */
    public static final String LINE_CONNECT = "lineConnect";
    /** ログインチェック用ID */
    public static final String ID = "id";
  }

  /**
   * COLOR定義用クラス
   */
  public static class ColorConst {
    /** デフォルトカラー(薄い灰色) */
    public static final String DEFAULT_COLOR = "#999999";
    /** 黒 */
    public static final String BLACK = "#000000";
    /** 赤 */
    public static final String RED = "#ff0000";
    /** 青 */
    public static final String BLUE = "#0000ff";
  }

  public static class AlterMessage {
    public static final String FLEX_MESSAGE = "new message";
  }
}
