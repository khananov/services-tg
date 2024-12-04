package ru.khananov.tg.handlers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.khananov.tg.common.enums.UserState;
import ru.khananov.tg.handlers.UpdateHandler;
import ru.khananov.tg.common.enums.CommandType;
import ru.khananov.tg.common.keyboards.MainMenuReplyKeyboardMarkup;
import ru.khananov.tg.services.TelegramService;
import ru.khananov.tg.services.TelegramUserService;

import static ru.khananov.tg.common.enums.UserState.START;

@Component
public class StartUpdateHandler implements UpdateHandler {
  private final TelegramService telegramService;
  private final TelegramUserService telegramUserService;

  @Autowired
  public StartUpdateHandler(TelegramService telegramService, TelegramUserService telegramUserService) {
    this.telegramService = telegramService;
    this.telegramUserService = telegramUserService;
  }

  @Override
  public void handleUpdate(Update update) {
    if (!update.hasMessage() || update.getMessage().getText() == null) return;

    Message message = update.getMessage();
    Long chatId = message.getChatId();
    String messageText = message.getText();
    if (CommandType.START_COMMAND.getValue().equals(messageText)) {
      handleStartCommand(message, chatId);
    } else if (CommandType.CANCEL_COMMAND.getValue().equals(messageText)) {
      handleCancelCommand(chatId);
    }
  }

  private void handleStartCommand(Message message, Long chatId) {
    telegramUserService.createByMessageAndSave(message);
    sendMainMenuKeyboard(chatId);
  }

  private void handleCancelCommand(Long chatId) {
    telegramUserService.updateUserState(chatId, START);
    sendMainMenuKeyboard(chatId);
  }

  private void sendMainMenuKeyboard(Long chatId) {
    telegramService.sendReplyKeyboard(MainMenuReplyKeyboardMarkup.getInstance(),
        "Выберите сервис",
        chatId);
  }
}
