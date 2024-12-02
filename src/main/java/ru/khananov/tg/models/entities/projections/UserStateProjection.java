package ru.khananov.tg.models.entities.projections;

import ru.khananov.tg.models.enums.UserState;

public interface UserStateProjection {
  UserState getUserState();
}
