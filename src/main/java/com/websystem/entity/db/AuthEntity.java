package com.websystem.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 認証情報エンティティ
 */
@Entity
@Table(name = "auth", schema = "public")
public class AuthEntity {

  /** ユーザID */
  @Id
  @Column(name = "user_id")
  private String userId;

  /** パスワード */
  @Column(name = "password")
  private String password;

  /** LINE_ID */
  @Column(name = "line_id")
  private String lineId;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLineId() {
    return lineId;
  }

  public void setLineId(String lineId) {
    this.lineId = lineId;
  }
}
