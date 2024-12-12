package ru.khananov.tg.common.enums;

public enum CommandType {
  START_COMMAND("/start"),

  INVEST_COMMAND("\uD83D\uDCC8 Инвестиции"),
  TRANSLATOR_COMMAND("\uD83C\uDE02\uFE0F Переводчик"),
  WEATHER_COMMAND("\uD83C\uDF21\uFE0F Погода"),

  CONFIRM_COMMAND("✔\uFE0F Да"),
  REJECT_COMMAND("❌ Нет"),
  CANCEL_COMMAND("\uD83D\uDEAB Отменить");

  private final String value;

  CommandType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static boolean hasCommand(String command) {
    if (command == null || command.isEmpty()) return false;
    for (int i = 0; i < values().length; i++) {
      if (values()[i].value.equals(command)) return true;
    }
    return false;
  }
}
