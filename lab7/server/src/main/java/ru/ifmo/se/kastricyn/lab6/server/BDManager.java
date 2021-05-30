package ru.ifmo.se.kastricyn.lab6.server;

import com.sun.istack.internal.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class BDManager {
    static final Logger log = LogManager.getLogger();
    static Properties properties = Properties.getProperties();

    /**
     * Возвращает соединение с БД по параметрам из конфигурационного файла
     */
    @Nullable
    public static Connection setConnection() {
        try {
            Class.forName("org.postgresql.Driver");
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

    public static void initialTable(Connection connection){

    }
}
