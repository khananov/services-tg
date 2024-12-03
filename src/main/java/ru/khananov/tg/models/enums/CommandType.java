package ru.khananov.tg.models.enums;

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
}
