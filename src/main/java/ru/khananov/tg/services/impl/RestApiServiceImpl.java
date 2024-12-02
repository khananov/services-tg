package ru.khananov.tg.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.khananov.tg.services.RestApiService;

@Service
public class RestApiServiceImpl implements RestApiService {
  private final RestTemplate restTemplate;

  @Autowired
  public RestApiServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public <T> T sendPostRequest(String url, Object requestValue, Class<T> responseType) {
    return restTemplate.postForObject(url, requestValue, responseType);
  }
}
