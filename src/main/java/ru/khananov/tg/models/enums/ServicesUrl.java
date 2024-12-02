package ru.khananov.tg.models.enums;

public enum ServicesUrl {
  TRANSLATOR_SERVICE_URL("http://localhost:8081/");

  private final String value;

  ServicesUrl(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
