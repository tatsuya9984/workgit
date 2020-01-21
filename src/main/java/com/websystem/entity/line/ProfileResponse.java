package com.websystem.entity.line;

/**
 * プロフィール情報取得APIレスポンス
 * 参考：https://developers.line.biz/ja/docs/social-api/getting-user-profiles/
 */
public class ProfileResponse {
  /** LineID */
  private String userId;
  /** ディスプレイ名 */
  private String displayName;
  /** トプ画パス */
  private String pictureUrl;
  /** 一言 */
  private String statusMessage;

  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getDisplayName() {
    return displayName;
  }
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
  public String getPictureUrl() {
    return pictureUrl;
  }
  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }
  public String getStatusMessage() {
    return statusMessage;
  }
  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }
}
