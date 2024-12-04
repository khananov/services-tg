package ru.khananov.tg.handlers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.khananov.tg.handlers.UpdateHandler;
import ru.khananov.tg.common.keyboards.CancelReplyKeyboardMarkup;
import ru.khananov.tg.services.RestApiService;
import ru.khananov.tg.services.TelegramService;
import ru.khananov.tg.services.TelegramUserService;

import static ru.khananov.tg.common.enums.CommandType.TRANSLATOR_COMMAND;
import static ru.khananov.tg.common.enums.CommandType.hasCommand;
import static ru.khananov.tg.common.enums.UserState.TRANSLATOR;

@Component
public class TranslatorUpdateHandler implements UpdateHandler {
  private final TelegramService telegramService;
  private final TelegramUserService telegramUserService;
  private final RestApiService restApiService;

  @Value("${translator.service.url}")
  private String translatorServiceUrl;

  @Autowired
  public TranslatorUpdateHandler(TelegramService telegramService, TelegramUserService telegramUserService, RestApiService restApiService) {
    this.telegramService = telegramService;
    this.telegramUserService = telegramUserService;
    this.restApiService = restApiService;
  }

  @Override
  public void handleUpdate(Update update) {
    if (!update.hasMessage() || update.getMessage().getText() == null) return;

    Long chatId = update.getMessage().getChatId();
    String messageText = update.getMessage().getText();
    if (TRANSLATOR_COMMAND.getValue().equals(messageText)) {
      handleTranslatorCommand(chatId);
    } else if (!hasCommand(messageText) && TRANSLATOR.equals(telegramUserService.getUserStateByChatId(chatId))) { // TODO переделать решение со state - придется искать в каждом handler
      handleTranslatorState(messageText, chatId);
    }
  }

  private void handleTranslatorCommand(Long chatId) {
    telegramService.sendReplyKeyboard(CancelReplyKeyboardMarkup.getInstance(), "Отправьте текст для перевода", chatId);
    telegramUserService.updateUserState(chatId, TRANSLATOR);
  }

  private void handleTranslatorState(String messageText, Long chatId) {
    String translatedMessageResponse = restApiService.sendPostRequest(translatorServiceUrl + "/translate", messageText, String.class);
    telegramService.sendMessage(chatId, translatedMessageResponse);
  }
}
