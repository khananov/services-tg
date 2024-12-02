package ru.khananov.tg.controllers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface AbstractController {
  void execute(Update update);
}
