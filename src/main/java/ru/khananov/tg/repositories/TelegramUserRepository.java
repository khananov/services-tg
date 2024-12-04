package ru.khananov.tg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khananov.tg.common.entities.TelegramUser;
import ru.khananov.tg.common.entities.projections.UserStateProjection;

import java.util.Optional;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
  Optional<TelegramUser> findTelegramUserByChatId(Long chatId);

  Optional<UserStateProjection> getUserStateByChatId(Long chatId);
}
