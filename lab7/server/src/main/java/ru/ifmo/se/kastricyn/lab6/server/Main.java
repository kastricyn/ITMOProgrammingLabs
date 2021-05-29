package ru.ifmo.se.kastricyn.lab6.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ifmo.se.kastricyn.lab6.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Console;
import ru.ifmo.se.kastricyn.lab6.lib.utility.Parser;
import ru.ifmo.se.kastricyn.lab6.server.commandManager.ConsoleCommandManager;
import ru.ifmo.se.kastricyn.lab6.server.commandManager.NetCommandManager;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        if (args.length != 1) {
            System.out.println("Программа принимает на вход ровно один аргумент - путь до файла.\n" +
                    " Пожалйста проверьте верность аргументов и повторите запуск.");
            return;
        }

        Path p = Paths.get(args[0]);
        TicketCollection tickets = new TicketCollection();

        try {
            if (Files.exists(p))
                tickets = Parser.get(p, TicketCollection.class);
            else if (Files.notExists(p))
                tickets = Parser.create(p, TicketCollection.class);
            if (!Files.isWritable(p))
                System.out.println("Файл не доступен для записи.");
        } catch (JAXBException e) {
            log.warn("Нарушена структура файла коллекции");
            System.out.println("Нарушена структура файла, для работоспособности программы верните правильную структуру" +
                    "\n или удалите файл и мы создадим новый с пустой структурой по указанному пути. " +
                    "\n После исправления повторите попытку.");
            return;
        } catch (AccessDeniedException e) {
            System.out.println("Недостаточно прав на чтение файла, возможна работа с пустой коллекцией без сохранения.");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так во время создания файла.");
        }

        tickets.setPath(p).check();
        Scanner in = new Scanner(System.in);


        CommandManager netCommandManager = NetCommandManager.getStandards(tickets);
        Thread netConnections = new Thread(netCommandManager);
        try {

            netConnections.start();
            System.out.println("Данные для подключения: " + netCommandManager);

            CommandManager consoleCommandManager = ConsoleCommandManager.getStandards(tickets, new Console(in));
            consoleCommandManager.run();

        } catch (NoSuchElementException e) {
            System.out.println("Программа звершена пользователем.");
        } finally {
            netConnections.interrupt();
            log.info("stop");
        }
    }
}
