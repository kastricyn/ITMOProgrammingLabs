package ru.ifmo.se.kastricyn.lab5;

import ru.ifmo.se.kastricyn.lab5.utility.Console;

import javax.xml.bind.JAXBException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main-class
 */
public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Программа принимает на вход ровно один аргумент - путь до файла.\n" +
                    " Пожалйста проверьте верность аргументов и повторите запуск.");
            return;
        }

        Path p = Paths.get(args[0]);
        TicketCollection tickets = new TicketCollection();

        try {
            if (Files.exists(p))
                tickets = TicketCollection.getTicketCollection(p);
            else if (Files.notExists(p))
                tickets = TicketCollection.createTicketCollection(p);
            if (!Files.isWritable(p))
                System.out.println("Файл не доступен для записи.");
        } catch (JAXBException e) {
            System.out.println("Нарушена структура файла, для работоспособности программы верните правильную структуру" +
                    "\n или удалите файл и мы создадим новый с пустой структурой по указанному пути. " +
                    "\n После исправления повторите попытку.");
            return;
        } catch (AccessDeniedException e) {
            System.out.println("Недостаточно прав на чтение файла, возможна работа с пустой коллекцией без сохранения.");
        }

        tickets.check();
        Scanner in = new Scanner(System.in);

        CommandManager consoleCommandManager = CommandManager.createCommandManager(tickets, new Console(in));
        consoleCommandManager.run();
    }
}
