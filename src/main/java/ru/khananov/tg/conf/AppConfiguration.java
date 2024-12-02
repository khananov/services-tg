package ru.khananov.tg.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class AppConfiguration {
  @Value("${telegram.bot.token}")
  private String botToken;
  @Value("${telegram.bot.username}")
  private String botUsername;

  @Bean
  public String botToken() {
    return botToken;
  }

  @Bean
  public String botUsername() {
    return botUsername;
  }

  @Bean
  public TelegramBotsApi telegramBotsApi(TelegramPolling telegramPolling) throws TelegramApiException {
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
    telegramBotsApi.registerBot(telegramPolling);
    return telegramBotsApi;
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
