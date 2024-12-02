package ru.khananov.tg.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.khananov.tg.controllers.AbstractController;
import ru.khananov.tg.models.enums.CommandType;
import ru.khananov.tg.models.keyboards.MainMenuReplyKeyboardMarkup;
import ru.khananov.tg.services.TelegramService;
import ru.khananov.tg.services.TelegramUserService;

@Component
public class StartController implements AbstractController {
  private final TelegramService telegramService;
  private final TelegramUserService telegramUserService;

  @Autowired
  public StartController(TelegramService telegramService, TelegramUserService telegramUserService) {
    this.telegramService = telegramService;
    this.telegramUserService = telegramUserService;
  }

  @Override
  public void execute(Update update) {
    if (!update.hasMessage() || update.getMessage().getText() == null) return;

    String messageText = update.getMessage().getText();
    if (CommandType.START_COMMAND.getValue().equals(messageText) ||
        CommandType.CANCEL_COMMAND.getValue().equals(messageText)) {
      telegramUserService.createByMessageAndSave(update.getMessage());
      telegramService.sendReplyKeyboard(MainMenuReplyKeyboardMarkup.getInstance(),
          "Выберите сервис",
          update.getMessage().getChatId());
    }
  }
}
