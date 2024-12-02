package ru.khananov.tg.models.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.khananov.tg.models.enums.CommandType;

import java.util.Arrays;

import static org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton.builder;

public final class MainMenuReplyKeyboardMarkup {
  private static final ReplyKeyboardMarkup INSTANCE = createMainMenuReplyKeyboardMarkup();

  private MainMenuReplyKeyboardMarkup() {
  }

  public static ReplyKeyboardMarkup getInstance() {
    return INSTANCE;
  }

  private static ReplyKeyboardMarkup createMainMenuReplyKeyboardMarkup() {
    ReplyKeyboardMarkup.ReplyKeyboardMarkupBuilder keyboardBuilder = ReplyKeyboardMarkup.builder();
    keyboardBuilder.resizeKeyboard(true);
    keyboardBuilder.selective(true);
    keyboardBuilder.keyboardRow(new KeyboardRow(Arrays.asList(
        builder().text(CommandType.INVEST_COMMAND.getValue()).build())));

    return keyboardBuilder.build();
  }
}
