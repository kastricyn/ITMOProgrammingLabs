package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

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
        super("add", "добавить новый элемент в коллекцию");
        this.in = in;
        this.shouldPrintHints = shouldPrintHints;
        this.ticketCollection = ticketCollection;
        args.add(Ticket.class);
    }


    @Override
    public void execute(String ... args) {
        Ticket t = (Ticket) this.args.get(0);
        ticketCollection.add(t);
        System.out.println("В коллекцю добавлен объект " + t);
        ticketCollection.setSaved(false);
        this.args.clear();
    }

}
