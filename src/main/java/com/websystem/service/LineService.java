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

import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class LineService {
  @Value("${lineservice.oauth.authorize}") 
  private String authorizeURL;

  private OkHttpClient mClient = new OkHttpClient.Builder()
      .addNetworkInterceptor(new Interceptor() {
          @Override
          public Response intercept(Chain chain) throws IOException {
              return chain.proceed(chain.request());
          }
      })
      .build();

  public void authorize() throws IOException {
    Response r = mClient.newCall(new Request.Builder().url(authorizeURL).build()).execute();
    System.out.println("$$$$$$$$$$$$$$$$$$$$$");
    System.out.println(r.request().url().toString());
  }
}
