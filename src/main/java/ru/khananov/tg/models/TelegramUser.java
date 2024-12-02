package ru.khananov.tg.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.khananov.tg.models.enums.UserStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "telegramusers")
public class TelegramUser implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "chat_id")
  private Long chatId;

  @Column(name = "user_status")
  @Enumerated(EnumType.STRING)
  private UserStatus userStatus;

  public TelegramUser(Long id, Long userId, String firstName, String lastName, String phoneNumber, Long chatId, UserStatus userStatus) {
    this.id = id;
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.chatId = chatId;
    this.userStatus = userStatus;
  }

  public TelegramUser() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Long getChatId() {
    return chatId;
  }

  public void setChatId(Long chatId) {
    this.chatId = chatId;
  }

  public UserStatus getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(UserStatus userStatus) {
    this.userStatus = userStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TelegramUser that = (TelegramUser) o;
    return Objects.equals(userId, that.userId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(chatId, that.chatId) && userStatus == that.userStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, phoneNumber, chatId, userStatus);
  }
}
