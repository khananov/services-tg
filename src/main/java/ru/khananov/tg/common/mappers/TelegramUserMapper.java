package ru.khananov.tg.common.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.khananov.tg.common.entities.TelegramUser;

@Mapper(componentModel = "spring")
public interface TelegramUserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "contact.userId", target = "userId")
  @Mapping(source = "contact.firstName", target = "firstName")
  @Mapping(source = "contact.lastName", target = "lastName")
  @Mapping(source = "contact.phoneNumber", target = "phoneNumber")
  @Mapping(source = "chatId", target = "chatId")
  TelegramUser toTelegramUser(Message message);
}
