package ru.khananov.tg.models.enums;

public enum CommandType {
  START_COMMAND("/start"),
  MAIN_MENU_COMMAND("\uD83D\uDEAA Главная"),

  INVEST_COMMAND("\uD83D\uDECD Инвестиции"),

  TRANSLATOR_COMMAND("\uD83D\uDECD Переводчик"),
  TRANSLATOR_TRANSLATE_COMMAND("Перевести"),

  CONFIRM_COMMAND("Да"),
  REJECT_COMMAND("Нет"),
  CANCEL_COMMAND("Отменить");

  private final String value;

  CommandType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
