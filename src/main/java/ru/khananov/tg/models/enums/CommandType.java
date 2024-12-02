package ru.khananov.tg.models.enums;

public enum CommandType {
  START_COMMAND("/start"),
  MAIN_MENU_COMMAND("\uD83D\uDEAA Главная"),

  INVEST_COMMAND("\uD83D\uDECD Инвестиции"),

  CONFIRM_COMMAND("Да"),
  REJECT_COMMAND("Нет");

  private final String value;

  CommandType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
