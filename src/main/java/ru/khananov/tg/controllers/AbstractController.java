package ru.khananov.tg.controllers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface AbstractController {
  boolean isSupport(Update update);

  void process(Update update);
}
