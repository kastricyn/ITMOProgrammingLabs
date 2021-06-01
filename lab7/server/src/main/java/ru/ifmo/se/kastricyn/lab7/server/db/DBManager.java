package ru.ifmo.se.kastricyn.lab7.server.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.lib.User;
import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.server.Properties;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class DBManager implements DBTicketsI, DBUserI {
    static final String DB_DRIVER = "org.postgresql.Driver";
    static final Logger log = LogManager.getLogger(DBManager.class);
    static Properties properties = Properties.getProperties();

    /**
     * Возвращает соединение с БД по параметрам из конфигурационного файла
     */
    public static @Nullable Connection setConnection() {
        try {
            log.info("Попытка подключиться к БД.");
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Не найден класс с драйвером БД: " + Arrays.toString(e.getStackTrace()));
        }
        try {
            Connection connection = DriverManager.getConnection(properties.getDBUrl(), properties.getDBLogin(), properties.getDBPass());
            log.info("Подключено к БД: " + properties.getDBUrl());
            return connection;
        } catch (SQLException throwable) {
            log.error(throwable);
        }
        return null;
    }

    public static void initialTable(@NotNull Connection connection) {
        try {
            Statement stat = connection.createStatement();

        } catch (SQLException throwable) {
            log.error("Не удалось создать таблицы по необходимости" + Arrays.toString(throwable.getStackTrace()));
        }

    }

    /**
     * Создаёт необходимые таблицы
     */
    @Override
    public void createTables() {

    }

    /**
     * Добавляет в БД билет
     *
     * @param t билет, который надо добавить
     * @return id под которым был добавлен билет, -1 если билет не был добавлен
     */
    @Override
    public long addTicket(@NotNull Ticket t) {
        return 0;
    }

    /**
     * Обновляет в БД билет
     *
     * @param id номер билетв
     * @param t  новый билет
     * @return true если добавлено, иначе false
     */
    @Override
    public boolean updateTicket(long id, @NotNull Ticket t) {
        return false;
    }

    /**
     * Удаляет в БД билет
     *
     * @param id номер билетв
     * @return true если удалено, иначе false
     */
    @Override
    public boolean deleteTicket(long id) {
        return false;
    }

    /**
     * Возвращает коллецию билетов из БД.
     */
    @Override
    public @NotNull TicketCollection getTicketCollection() {
        return null;
    }

    /**
     * Удаляет все элементы пользователя
     *
     * @param userId
     */
    @Override
    public boolean clear(long userId) {
        return false;
    }

    /**
     * Авторизует пользователя
     *
     * @param user пользователь, которого необходимо авторизовать
     * @return возвращает пользователя из записи БД, если прошёл авторизацию, null иначе
     */
    @Override
    public @Nullable User auth(User user) {
        return null;
    }

    /**
     * Добавляет нового пользователя в БД
     *
     * @param user пользователь
     * @return true если добавлено, иначе false
     */
    @Override
    public boolean addUser(User user) {
        return false;
    }

    /**
     * Проверяет пароль пользователя на совпадение с данными в БД
     *
     * @param user пользователь
     * @return true, если пароль верный; иначе false
     */
    @Override
    public boolean checkPassword(User user) {
        return false;
    }

    /**
     * Возвращает id пользователя
     *
     * @param user пользователь
     * @return id пользователя, или -1 если пользователь не найден в базе
     */
    @Override
    public long getId(User user) {
        return 0;
    }
}
