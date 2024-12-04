package ru.khananov.tg.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {
  void handleUpdate(Update update);
}
