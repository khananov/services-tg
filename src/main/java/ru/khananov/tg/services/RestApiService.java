package ru.khananov.tg.services;

public interface RestApiService {
  <T> T sendPostRequest(String url, Object requestValue, Class<T> responseType);
}
