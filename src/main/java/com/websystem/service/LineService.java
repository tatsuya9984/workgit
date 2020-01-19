package com.websystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.websystem.entity.line.AuthorizeEntity;

@Service
public class LineService {
  @Autowired
  private RestTemplate restTemplate;
  @Value("${lineservice.oauth.authorize}") 
  private String authorizeURL;

  public void authorize() {
    AuthorizeEntity authorizeEntity = restTemplate.getForObject(authorizeURL, AuthorizeEntity.class);
    System.out.println("$$$$$$$$$$$$$$$$$$$$$");
    System.out.println(authorizeEntity.getCode());
  }
}