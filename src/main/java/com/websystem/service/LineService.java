package com.websystem.service;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.websystem.entity.line.AuthorizeEntity;
import com.websystem.entity.line.ProfileResponse;
import com.websystem.entity.line.TokenRequest;
import com.websystem.entity.line.TokenResponse;

import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class LineService {
  @Value("${lineservice.oauth.authorize}") 
  private String authorizeURL;
  @Value("${lineservice.profile}")
  private String getProfileURL;

  public TokenResponse getToken(String code) {
    RestTemplate restTemplate = new RestTemplate();
    MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
    map.add("grant_type", "authorization_code");
    map.add("code", code);
    map.add("redirect_uri", "https://fassion-talk.herokuapp.com/lineconnect/callback");
    map.add("client_id", "1653778420");
    map.add("client_secret", "d21735f1b7c0c395f9fedc5075ba4f8f");

    RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(URI.create("https://api.line.me/oauth2/v2.1/token"))
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(map);

    return restTemplate.exchange(request, TokenResponse.class).getBody();
  }

  public ProfileResponse getProfile(String accessToken) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer "+accessToken);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    ResponseEntity<ProfileResponse> response = restTemplate.exchange(getProfileURL, HttpMethod.GET, entity, ProfileResponse.class);
    return response.getBody();
  }
}
