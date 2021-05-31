package ru.ifmo.se.kastricyn.lab7.server.db;

import ru.ifmo.se.kastricyn.lab7.lib.User;

public interface DBUserI {
    /**
     * Добавляет нового пользователя в БД
     * @param user пользователь
     * @return true если добавлено, иначе false
     */
    boolean addUser(User user);

    /**
     * Проверяет пароль пользователя на совпадение с данными в БД
     * @param user пользователь
     * @return true, если пароль верный; иначе false
     */
    boolean checkPassword(User user);
}
