package ru.khananov.tg.exceptions;

public class UnsupportedMessageTypeException extends RuntimeException {
  public UnsupportedMessageTypeException() {
    super("Unsupported message type");
  }
}
