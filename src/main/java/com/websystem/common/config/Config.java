package com.websystem.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
  @Value("${lineservice.getProfileURL}")
  private String getProfileURL;
  @Value("${lineservice.getAccessTokenURL}")
  private String getAccessTokenURL;
  @Value("${websystem.baseURL}")
  private String baseURL;
  @Value("${lineservice.loginChannel.clientID}")
  private String clientID;
  @Value("${lineservice.loginChannel.clientSecret}")
  private String clientSecret;

  public String getGetProfileURL() {
    return getProfileURL;
  }
  public String getAccessTokenURL() {
    return getAccessTokenURL;
  }
  public String getBaseURL() {
    return baseURL;
  }
  public String getClientID() {
    return clientID;
  }
  public String getClientSecret() {
    return clientSecret;
  }
}
