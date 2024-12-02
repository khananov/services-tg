package ru.khananov.tg.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.khananov.tg.models.mappers.TelegramUserMapper;
import ru.khananov.tg.models.TelegramUser;
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
  public TelegramUser createByMessageAndSave(Message message) {
    Optional<TelegramUser> telegramUserOptional = telegramUserRepository.findTelegramUserByChatId(message.getChatId());
    telegramUserOptional.ifPresent(tgUser -> updateContactTelegramUser(tgUser, message));
    TelegramUser telegramUser = telegramUserOptional.orElse(telegramUserMapper.toTelegramUser(message));
    telegramUser.setUserStatus(UserStatus.ACTIVE);
    return telegramUserRepository.save(telegramUser);
  }

  private void updateContactTelegramUser(TelegramUser telegramUser, Message message) {
    if (message.getContact() != null) {
      telegramUser.setUserId(message.getContact().getUserId());
      telegramUser.setFirstName(message.getContact().getFirstName());
      telegramUser.setLastName(message.getContact().getLastName());
      telegramUser.setPhoneNumber(message.getContact().getPhoneNumber());
    }
  }
}
