package ru.khananov.tg.conf;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.khananov.tg.controllers.AbstractController;
import ru.khananov.tg.exceptions.UnsupportedMessageTypeException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramPolling extends TelegramLongPollingBot {
  private final List<AbstractController> telegramControllers;
  private final String botUsername;

  public TelegramPolling(String botToken, String botUsername, ApplicationContext context) {
    super(botToken);
    this.botUsername = botUsername;
    this.telegramControllers = new ArrayList<>(context.getBeansOfType(AbstractController.class).values());
  }

  @Override
  public void onUpdateReceived(Update update) {
    telegramControllers.forEach(controller -> controller.execute(update));
  }

  @Override
  public String getBotUsername() {
    return botUsername;
  }
}
