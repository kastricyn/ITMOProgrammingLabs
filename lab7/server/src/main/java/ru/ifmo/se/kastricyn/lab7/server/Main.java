package ru.ifmo.se.kastricyn.lab7.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ifmo.se.kastricyn.lab7.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab7.lib.exception.DBConnectionException;
import ru.ifmo.se.kastricyn.lab7.server.commandManager.NetCommandManager;
import ru.ifmo.se.kastricyn.lab7.server.db.DBManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

/**
 * Main-class
 */
public class Main {
    static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        log.info("start");
        try {
        Properties.getProperties().load(Paths.get("config"));

        DBManager dbManager = new DBManager();
        TicketCollection tickets = dbManager.getTicketCollection();

        CommandManager netCommandManager = NetCommandManager.getStandards(tickets);
        Thread netConnections = new Thread(netCommandManager);
        netConnections.setName("ServerThread");
        try {
            netConnections.start();

            ru.ifmo.se.kastricyn.lab7.client.Main.main(new String[1]);

        } catch (DBConnectionException e) {
            log.error("Не удаётся подключиться к БД, к сожалению, программа не может работать без БД.");
        } catch (NoSuchElementException e) {
            System.out.println("Программа звершена пользователем.");
        } finally {
            netConnections.interrupt();
            dbManager.close();
            log.info("stop");
        }}
        catch (FileNotFoundException e){
            log.error("Не найден файл config");
        } catch (DBConnectionException e) {
            // todo временная недоступность BD
            log.error("Программа завершена из-за ошибки соединения с базой данных.");
        }
    }
}
