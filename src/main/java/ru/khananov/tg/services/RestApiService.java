package ru.khananov.tg.services;

import java.util.Map;

public interface RestApiService {
  <T> T sendPostRequest(String url, Object requestValue, Class<T> responseType);

  <T> T sendGetRequest(String url, Map<String, ?> requestParams, Class<T> responseType);
}
