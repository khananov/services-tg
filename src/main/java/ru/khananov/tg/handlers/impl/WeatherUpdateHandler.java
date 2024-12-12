package ru.khananov.tg.handlers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.khananov.tg.common.keyboards.CancelReplyKeyboardMarkup;
import ru.khananov.tg.handlers.UpdateHandler;
import ru.khananov.tg.services.RestApiService;
import ru.khananov.tg.services.TelegramService;
import ru.khananov.tg.services.TelegramUserService;

import java.util.Map;

import static ru.khananov.tg.common.enums.CommandType.WEATHER_COMMAND;
import static ru.khananov.tg.common.enums.UserState.WEATHER;

@Component
public class WeatherUpdateHandler implements UpdateHandler {
  private final TelegramService telegramService;
  private final TelegramUserService telegramUserService;
  private final RestApiService restApiService;

  @Value("${weather.service.url}")
  private String weatherServiceUrl;

  @Autowired
  public WeatherUpdateHandler(TelegramService telegramService, TelegramUserService telegramUserService, RestApiService restApiService) {
    this.telegramService = telegramService;
    this.telegramUserService = telegramUserService;
    this.restApiService = restApiService;
  }

  @Override
  public void handleUpdate(Update update) {
    if (!update.hasMessage()) return;

    Long chatId = update.getMessage().getChatId();
    Location location = update.getMessage().getLocation();
    if (WEATHER_COMMAND.getValue().equals(update.getMessage().getText())) {
      handleWeatherCommand(chatId);
    } else if (location != null && WEATHER.equals(telegramUserService.getUserStateByChatId(chatId))) { // TODO переделать решение со state - придется искать в каждом handler
      handleWeatherState(location, chatId);
    }
  }

  private void handleWeatherCommand(Long chatId) {
    telegramService.sendReplyKeyboard(CancelReplyKeyboardMarkup.getInstance(), "Отправьте геопозицию следующим сообщением", chatId);
    telegramUserService.updateUserState(chatId, WEATHER);
  }

  private void handleWeatherState(Location location, Long chatId) {
    Map<String, Double> params = Map.of(
        "latitude", location.getLatitude(),
        "longitude", location.getLongitude());
    String weatherResponse = restApiService.sendGetRequest(weatherServiceUrl + "/getWeather", params, String.class);
    telegramService.sendMessage(chatId, weatherResponse);
  }
}
