package ru.khananov.tg.handlers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.khananov.tg.handlers.UpdateHandler;
import ru.khananov.tg.services.RestApiService;
import ru.khananov.tg.services.TelegramService;

@Component
public class WeatherUpdateHandler implements UpdateHandler {
  private final TelegramService telegramService;
  private final RestApiService restApiService;

  @Autowired
  public WeatherUpdateHandler(TelegramService telegramService, RestApiService restApiService) {
    this.telegramService = telegramService;
    this.restApiService = restApiService;
  }

  @Override
  public void handleUpdate(Update update) {

  }
}
