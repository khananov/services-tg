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
  public boolean isSupport(Update update) {
    if (update.hasMessage()) {
      return CommandType.START_COMMAND.getValue().equals(update.getMessage().getText());
    }
    return false;
  }

  @Override
  public void process(Update update) {
    telegramUserService.createByMessageAndSave(update.getMessage());
    sendStartMessage(update.getMessage().getChatId());
  }

  private void sendStartMessage(Long chatId) {
    telegramService.sendReplyKeyboard(MainMenuReplyKeyboardMarkup.getInstance(),
        "Выберите действие",
        chatId);
  }
}
