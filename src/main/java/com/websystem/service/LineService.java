package com.websystem.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.websystem.common.config.Config;
import com.websystem.entity.line.ProfileResponse;
import com.websystem.entity.line.TokenResponse;

/**
 * LINEサービス
 */
@Service
public class LineService {
  @Autowired
  private Config config;

  /**
   * アクセストークンを取得する
   * 
   * @param code 許可コード
   * @param path ベースパス以降のパス
   * @return
   */
  public TokenResponse getToken(String code, String path) {
    RestTemplate restTemplate = new RestTemplate();
    MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
    map.add("grant_type", "authorization_code");
    map.add("code", code);
    map.add("redirect_uri", config.getBaseURL() + "lineconnect/" + path);
    map.add("client_id", config.getClientID());
    map.add("client_secret", config.getClientSecret());

    // 必要情報を入れ込んでPOST送信用リクエストを作成    
    RequestEntity<MultiValueMap<String, String>> request = 
        RequestEntity.post(URI.create(config.getAccessTokenURL()))
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body(map);

    return restTemplate.exchange(request, TokenResponse.class).getBody();
  }

  /**
   * プロフィール情報を取得する
   * 
   * @param accessToken アクセストークン
   * @return
   */
  public ProfileResponse getProfile(String accessToken) {
    RestTemplate restTemplate = new RestTemplate();

    // ヘッダーの作成(アクセストークンを入れ込む)
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer "+accessToken);

    // ヘッダーを入れ込んだエンティティの作成
    HttpEntity<String> entity = new HttpEntity<String>(headers);

    // プロフィール情報取得API発行
    ResponseEntity<ProfileResponse> response =
        restTemplate.exchange(config.getGetProfileURL(), HttpMethod.GET, entity, ProfileResponse.class);
    return response.getBody();
  }
}
