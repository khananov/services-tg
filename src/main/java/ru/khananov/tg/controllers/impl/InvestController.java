package ru.khananov.tg.controllers.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.khananov.tg.controllers.AbstractController;
import ru.khananov.tg.models.enums.CommandType;

@Component
public class InvestController implements AbstractController {

  @Override
  public boolean isSupport(Update update) {
    if (update.hasMessage()) {
      return CommandType.INVEST_COMMAND.getValue().equals(update.getMessage().getText());
    }
    return false;
  }

  @Override
  public void process(Update update) {

  }
}
