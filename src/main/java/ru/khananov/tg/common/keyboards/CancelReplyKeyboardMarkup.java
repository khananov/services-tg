package ru.khananov.tg.common.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.khananov.tg.common.enums.CommandType;

import java.util.List;

import static org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton.builder;

public final class CancelReplyKeyboardMarkup {
  private static final ReplyKeyboardMarkup INSTANCE = createMainMenuReplyKeyboardMarkup();

  private CancelReplyKeyboardMarkup() {
  }

  public static ReplyKeyboardMarkup getInstance() {
    return INSTANCE;
  }

  private static ReplyKeyboardMarkup createMainMenuReplyKeyboardMarkup() {
    ReplyKeyboardMarkup.ReplyKeyboardMarkupBuilder keyboardBuilder = ReplyKeyboardMarkup.builder();
    keyboardBuilder.resizeKeyboard(true);
    keyboardBuilder.selective(true);
    keyboardBuilder.keyboardRow(new KeyboardRow(List.of(
        builder().text(CommandType.CANCEL_COMMAND.getValue()).build()
    )));

    return keyboardBuilder.build();
  }
}
