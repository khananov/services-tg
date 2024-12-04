package ru.khananov.tg.services;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.khananov.tg.common.enums.UserState;

public interface TelegramUserService {
  void createByMessageAndSave(Message message);

  UserState getUserStateByChatId(Long chatId);

  void updateUserState(Long chatId, UserState state);
}
