package ru.ifmo.se.kastricyn.lab7.server.db;

import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.lib.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public interface DBUserI {
    static final String needAuth = "Необходимо авторизоваться";

    /**
     * Авторизует пользователя
     * @param user пользователь, которого необходимо авторизовать
     * @return id пользователя из записи БД, если прошёл авторизацию, -1 иначе
     */
    @Nullable
    User auth(User user);

    /**
     * Добавляет нового пользователя в БД
     * @param user пользователь
     * @return true если добавлено, иначе false
     */
    boolean registerUser(User user);

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

    /**
     * Возвращает отпечаток по исходнику, используя SHA-1
     * @param convertme исходник
     * @param salt соль
     * @throws NoSuchAlgorithmException
     */
    default String getStringFromPassword(char[] convertme, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return Base64.getEncoder().encodeToString((md.digest(("006060.6.21.17:49" + new String(convertme) + salt).getBytes(StandardCharsets.UTF_8))));
    }
}
