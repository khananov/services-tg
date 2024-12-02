package ru.khananov.tg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khananov.tg.models.entities.TelegramUser;
import ru.khananov.tg.models.entities.projections.UserStateProjection;
import ru.khananov.tg.models.enums.UserState;

import java.util.Optional;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
  Optional<TelegramUser> findTelegramUserByChatId(Long chatId); // TODO подумать на счет использования Optional

  UserStateProjection getUserStateByChatId(Long chatId);
}
