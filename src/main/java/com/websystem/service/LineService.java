package com.websystem.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.websystem.entity.line.AuthorizeEntity;
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

  public TokenResponse getToken(String code) {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.postForObject(authorizeURL, new TokenRequest(code), TokenResponse.class);
  }
}
