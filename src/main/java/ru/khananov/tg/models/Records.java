package ru.khananov.tg.models;

public class Records {
  private Records() {
  }

  public record TranslatorRequest(String targetLanguageCode, String texts) {
  }
}
