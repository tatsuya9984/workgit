package com.websystem.entity.line;

public class AuthorizeEntity {
  public String code;
  public String state;
  public String friendship_status_changed;

  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getFriendship_status_changed() {
    return friendship_status_changed;
  }
  public void setFriendship_status_changed(String friendship_status_changed) {
    this.friendship_status_changed = friendship_status_changed;
  }
}
