package ru.khananov.tg.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.khananov.tg.controllers.AbstractController;
import ru.khananov.tg.models.Records;
import ru.khananov.tg.models.enums.CommandType;
import ru.khananov.tg.models.enums.ServicesUrl;
import ru.khananov.tg.models.enums.UserState;
import ru.khananov.tg.models.keyboards.CancelReplyKeyboardMarkup;
import ru.khananov.tg.services.RestApiService;
import ru.khananov.tg.services.TelegramService;
import ru.khananov.tg.services.TelegramUserService;

@Component
public class TranslatorController implements AbstractController {
  private final TelegramService telegramService;
  private final TelegramUserService telegramUserService;
  private final RestApiService restApiService;

  @Autowired
  public TranslatorController(TelegramService telegramService, TelegramUserService telegramUserService, RestApiService restApiService) {
    this.telegramService = telegramService;
    this.telegramUserService = telegramUserService;
    this.restApiService = restApiService;
  }

  @Override
  public void execute(Update update) {
    if (!update.hasMessage() || update.getMessage().getText() == null) return;

    Long chatId = update.getMessage().getChatId();
    String messageText = update.getMessage().getText();
    if (CommandType.TRANSLATOR_COMMAND.getValue().equals(messageText)) {
      telegramService.sendReplyKeyboard(CancelReplyKeyboardMarkup.getInstance(), "Отправьте текст для перевода", chatId);
      telegramUserService.updateUserState(chatId, UserState.TRANSLATOR);
    } else if (UserState.TRANSLATOR.equals(telegramUserService.getUserStateByChatId(chatId))) { // TODO неоптимальное решение, будем обращаться к БД при каждом обращении пользователя для каждого контроллера
      String translatedMessageResponse = restApiService.sendPostRequest(
          ServicesUrl.TRANSLATOR_SERVICE_URL.getValue() + "/translate",
          new Records.TranslatorRequest("ru", messageText),
          String.class);
      telegramService.sendMessage(chatId, translatedMessageResponse);
    }
  }
}
