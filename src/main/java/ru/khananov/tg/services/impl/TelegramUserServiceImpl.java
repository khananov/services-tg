package ru.khananov.tg.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.khananov.tg.models.enums.UserState;
import ru.khananov.tg.models.mappers.TelegramUserMapper;
import ru.khananov.tg.models.entities.TelegramUser;
import ru.khananov.tg.models.enums.UserStatus;
import ru.khananov.tg.repositories.TelegramUserRepository;
import ru.khananov.tg.services.TelegramUserService;

import java.util.Optional;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {
  private final TelegramUserRepository telegramUserRepository;
  private final TelegramUserMapper telegramUserMapper;

  @Autowired
  public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository, TelegramUserMapper telegramUserMapper) {
    this.telegramUserRepository = telegramUserRepository;
    this.telegramUserMapper = telegramUserMapper;
  }

  @Override
  public void createByMessageAndSave(Message message) {
    Optional<TelegramUser> telegramUserOptional = telegramUserRepository.findTelegramUserByChatId(message.getChatId());
    telegramUserOptional.ifPresent(tgUser -> updateTelegramUserContact(tgUser, message));
    TelegramUser telegramUser = telegramUserOptional.orElse(telegramUserMapper.toTelegramUser(message));
    telegramUser.setUserStatus(UserStatus.ACTIVE);
    telegramUser.setUserState(UserState.START);
    telegramUserRepository.save(telegramUser);
  }

  @Override
  public UserState getUserStateByChatId(Long chatId) {
    return telegramUserRepository.getUserStateByChatId(chatId).getUserState();
  }

  @Override
  public void updateUserState(Long chatId, UserState state) {
    Optional<TelegramUser> telegramUserOptional = telegramUserRepository.findTelegramUserByChatId(chatId);
    if (telegramUserOptional.isPresent()) {
      TelegramUser telegramUser = telegramUserOptional.get();
      telegramUser.setUserState(state);
      telegramUserRepository.save(telegramUser);
    }
  }

  private void updateTelegramUserContact(TelegramUser telegramUser, Message message) {
    if (message.getContact() != null) {
      telegramUser.setUserId(message.getContact().getUserId());
      telegramUser.setFirstName(message.getContact().getFirstName());
      telegramUser.setLastName(message.getContact().getLastName());
      telegramUser.setPhoneNumber(message.getContact().getPhoneNumber());
    }
  }
}
