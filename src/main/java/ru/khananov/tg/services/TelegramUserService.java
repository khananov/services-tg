package ru.khananov.tg.services;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.khananov.tg.models.TelegramUser;

public interface TelegramUserService {
  TelegramUser createByMessageAndSave(Message message);
}
