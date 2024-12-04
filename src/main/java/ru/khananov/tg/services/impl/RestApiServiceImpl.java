package ru.khananov.tg.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.khananov.tg.services.RestApiService;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
public class RestApiServiceImpl implements RestApiService {
  private final RestTemplate restTemplate;

  @Autowired
  public RestApiServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public <T> T sendPostRequest(String url, Object requestValue, Class<T> responseType) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
    HttpEntity<Object> entity = new HttpEntity<>(requestValue, headers);
    return restTemplate.postForObject(url, entity, responseType);
  }
}
