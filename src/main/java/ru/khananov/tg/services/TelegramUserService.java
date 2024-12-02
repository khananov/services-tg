package ru.khananov.tg.services;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.khananov.tg.models.entities.TelegramUser;

public interface TelegramUserService {
  void createByMessageAndSave(Message message);
}
