package ru.ifmo.se.kastricyn.lab7.server.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab7.server.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

// TODO: implements DBTicketsI, DBUserI
public class DBManager {
    static final Logger log = LogManager.getLogger();
    static Properties properties = Properties.getProperties();

    /**
     * Возвращает соединение с БД по параметрам из конфигурационного файла
     */
    //todo nullable
    public static @Nullable Connection setConnection() {
        try {
            Class.forName(properties.getDBDriver());
        } catch (ClassNotFoundException e) {
            log.error("Не найден класс с драйвером БД: " + Arrays.toString(e.getStackTrace()));
        }
        try {
            return DriverManager.getConnection(properties.getDBUrl(), properties.getDBLogin(), properties.getDBPass());
        } catch (SQLException throwable) {
            log.error(Arrays.toString(throwable.getStackTrace()));
        }
        return null;
    }

    public static void initialTable(@NotNull Connection connection){
        try {
            Statement stat = connection.createStatement();

        } catch (SQLException throwable) {
            log.error("Не удалось создать таблицы по необходимости" + Arrays.toString(throwable.getStackTrace()));
        }

    }
}
