package ru.ifmo.se.kastricyn.lab5.commands;

import ru.ifmo.se.kastricyn.lab5.TicketCollection;
import ru.ifmo.se.kastricyn.lab5.data.Ticket;
import ru.ifmo.se.kastricyn.lab5.utility.Console;

import java.util.Scanner;

/**
 * Команда добавить эллемент в коллекцию
 */
public class Add extends AbstractCommand {
    private Scanner in;
    private boolean shouldPrintHints;
    private TicketCollection ticketCollection;

    /**
     * Создаёт команду, которая для исполнения будет использовать переданные её аргументы
     */
    public Add(TicketCollection ticketCollection, Scanner in, boolean shouldPrintHints) {
        super("add", "add {element} \n - добавить новый элемент в коллекцию");
        this.in = in;
        this.shouldPrintHints = shouldPrintHints;
        this.ticketCollection = ticketCollection;
    }


    @Override
    public void execute(String ... args) {
        Ticket t = new Ticket(new Console(in, shouldPrintHints));
        ticketCollection.add(t);
        System.out.println("В коллекцию добавлен объект " + t);
        ticketCollection.setSaved(false);
    }
}
