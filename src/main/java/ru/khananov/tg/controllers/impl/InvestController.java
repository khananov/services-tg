package ru.khananov.tg.controllers.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.khananov.tg.controllers.AbstractController;
import ru.khananov.tg.models.enums.CommandType;

@Component
public class InvestController implements AbstractController {

  @Override
  public void execute(Update update) {

  }
}
