package ru.ifmo.se.kastricyn.lab7.server.db;

import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.lib.User;

public interface DBUserI {
    static final String needAuth = "Необходимо авторизоваться";

    /**
     * Авторизует пользователя
     * @param user пользователь, которого необходимо авторизовать
     * @return возвращает пользователя из записи БД, если прошёл авторизацию, null иначе
     */
    @Nullable
    User auth(User user);

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

    /**
     * Возвращает id пользователя
     * @param user пользователь
     * @return id пользователя, или -1 если пользователь не найден в базе
     */
    long getId(User user);
}
