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
  @Value("${lineservice.oauth.authorize}") 
  private String authorizeURL;

  public void authorize() {
    RestTemplate restTemplate = new RestTemplate();
    String authorizeEntity = restTemplate.getForObject(authorizeURL, String.class);
    System.out.println("$$$$$$$$$$$$$$$$$$$$$");
    System.out.println(authorizeEntity);
  }
}
