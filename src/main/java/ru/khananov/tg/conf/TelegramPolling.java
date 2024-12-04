package ru.khananov.tg.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.khananov.tg.handlers.UpdateHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramPolling extends TelegramLongPollingBot {
  private final List<UpdateHandler> telegramUpdateHandlers;
  private final String botUsername;

  @Autowired
  public TelegramPolling(String botToken, String botUsername, ApplicationContext context) {
    super(botToken);
    this.botUsername = botUsername;
    this.telegramUpdateHandlers = new ArrayList<>(context.getBeansOfType(UpdateHandler.class).values());
  }

  @Override
  public void onUpdateReceived(Update update) {
    telegramUpdateHandlers.forEach(handler -> handler.handleUpdate(update));
  }

  @Override
  public String getBotUsername() {
    return botUsername;
  }
}
