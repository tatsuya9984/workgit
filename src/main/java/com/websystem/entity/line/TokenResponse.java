package com.websystem.entity.line;

/**
 * アクセストークン発行APIのレスポンス
 * 参考：https://developers.line.biz/ja/docs/line-login/web/integrate-line-login-v2/#spy-anchor-07da3f28eee9339dce65f9bf4dbade89e22c1b74
 */
public class TokenResponse {
  /** アクセストークン */
  private String access_token;
  /** トークンID */
  private String id_token;

  public String getAccess_token() {
    return access_token;
  }
  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }
  public String getId_token() {
    return id_token;
  }
  public void setId_token(String id_token) {
    this.id_token = id_token;
  }
}
