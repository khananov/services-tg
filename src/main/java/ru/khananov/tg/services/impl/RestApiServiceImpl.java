package ru.khananov.tg.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.khananov.tg.services.RestApiService;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

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

  @Override
  public <T> T sendGetRequest(String url, Map<String, ?> requestParams, Class<T> responseType) {
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
    for (Map.Entry<String, ?> entry : requestParams.entrySet()) {
      builder.queryParam(entry.getKey(), entry.getValue());
    }
    return restTemplate.getForObject(builder.build().toUriString(), responseType);
  }
}
