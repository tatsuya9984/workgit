package com.websystem.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ユーザ情報エンティティ
 */
@Entity
@Table(name = "users", schema = "public")
public class UsersEntity {

  /** ユーザID */
  @Id
  @Column(name = "user_id")
  private String userId;

  /** 姓 */
  @Column(name = "family_name")
  private String familyName;

  /** 名 */
  @Column(name = "given_name")
  private String givenName;

  /** 通知用メールアドレス */
  @Column(name = "mail_address")
  private String mailAddress;

  /** メール配信フラグ */
  @Column(name = "sendable_flag")
  private Boolean sendableFlag;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getMailAddress() {
    return mailAddress;
  }

  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }

  public Boolean getSendableFlag() {
    return sendableFlag;
  }

  public void setSendableFlag(Boolean sendableFlag) {
    this.sendableFlag = sendableFlag;
  }
}
