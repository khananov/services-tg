package ru.khananov.tg.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.methods.invoices.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.khananov.tg.exceptions.SendMessageException;
import ru.khananov.tg.services.TelegramService;

import java.lang.invoke.MethodHandles;

@Service
public class TelegramServiceImpl extends DefaultAbsSender implements TelegramService {
  private static final Logger log = LogManager.getLogger(MethodHandles.lookup().lookupClass());

  protected TelegramServiceImpl(String botToken) {
    super(new DefaultBotOptions(), botToken);
  }

  @Override
  public void sendMessage(SendMessage message) {
    try {
      execute(message);
    } catch (TelegramApiException e) {
      throw new SendMessageException("Failed to send SendMessage: " + e.getMessage());
    }
  }

  @Override
  public void sendAnswerPreCheckoutQuery(AnswerPreCheckoutQuery preCheckoutQuery) {
    try {
      execute(preCheckoutQuery);
    } catch (TelegramApiException e) {
      throw new SendMessageException("Failed to send AnswerPreCheckoutQuery: " + e.getMessage());
    }
  }

  @Override
  public void sendInvoiceMessage(SendInvoice invoice) {
    try {
      execute(invoice);
    } catch (TelegramApiException e) {
      throw new SendMessageException("Failed to send SendInvoice: " + e.getMessage());
    }
  }

  public void sendEditMessage(EditMessageText message) {
    try {
      execute(message);
    } catch (TelegramApiException e) {
      throw new SendMessageException("Failed to send EditMessageText: " + e.getMessage());
    }
  }

  @Override
  public void sendPhoto(SendPhoto photo) {
    try {
      execute(photo);
    } catch (TelegramApiException e) {
      throw new SendMessageException("Failed to send SendPhoto: " + e.getMessage());
    }
  }

  @Override
  public void sendReplyKeyboard(ReplyKeyboardMarkup keyboardMarkup, String text, Long chatId) {
    SendMessage message = new SendMessage();
    message.setReplyMarkup(keyboardMarkup);
    message.setText(text);
    message.setChatId(chatId);
    sendMessage(message);
  }

  @Override
  public void sendInlineKeyboard(InlineKeyboardMarkup keyboardMarkup, String text, Long chatId) {
    SendMessage keyboardMarkupMessage = new SendMessage();
    keyboardMarkupMessage.setReplyMarkup(keyboardMarkup);
    keyboardMarkupMessage.setText(text);
    keyboardMarkupMessage.setChatId(chatId);
    sendMessage(keyboardMarkupMessage);
  }

  @Override
  public void deleteMessage(DeleteMessage message) {
    try {
      execute(message);
    } catch (TelegramApiException e) {
      throw new SendMessageException("Failed to send DeleteMessage: " + e.getMessage());
    }
  }

  @Override
  public void deleteReplyKeyboard(Long chatId) {
    ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
    replyKeyboardRemove.setRemoveKeyboard(true);

    SendMessage message = new SendMessage();
    message.setChatId(chatId);
    message.setReplyMarkup(replyKeyboardRemove);
    message.setText("Введите информацию о себе:");
    sendMessage(message);
  }
}
