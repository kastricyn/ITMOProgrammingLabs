package ru.ifmo.se.kastricyn.lab7.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ifmo.se.kastricyn.lab7.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab7.lib.exception.DBConnectionException;
import ru.ifmo.se.kastricyn.lab7.server.commandManager.NetCommandManager;
import ru.ifmo.se.kastricyn.lab7.server.db.DBManager;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main-class
 */
public class Main {
    static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        log.info("start");
        Properties.getProperties().load(Paths.get("config"));

        DBManager dbManager = new DBManager();
        TicketCollection tickets = dbManager.getTicketCollection();
        Scanner in = new Scanner(System.in);

        CommandManager netCommandManager = NetCommandManager.getStandards(tickets);
        Thread netConnections = new Thread(netCommandManager);
        try {
            netConnections.start();
            System.out.println("Данные для подключения: " + netCommandManager);

            ru.ifmo.se.kastricyn.lab7.client.Main.main(new String[1]);

        } catch (DBConnectionException e) {
            log.error("Не удаётся подключиться к БД, к сожалению, программа не может работать без БД.");
        } catch (NoSuchElementException e) {
            System.out.println("Программа звершена пользователем.");
        } finally {
            netConnections.interrupt();
            log.info("stop");
        }
    }
}
