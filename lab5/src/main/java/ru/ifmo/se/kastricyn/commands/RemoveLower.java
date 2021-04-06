package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;
import ru.ifmo.se.kastricyn.data.Ticket;
import ru.ifmo.se.kastricyn.utility.Console;

import java.util.Iterator;
import java.util.Scanner;

public class RemoveLower extends AbstractCommand {
    private Scanner in;
    private boolean shouldPrintHints;
    private TicketCollection ticketCollection;

    public RemoveLower(TicketCollection ticketCollection, Scanner in, boolean shouldPrintHints) {
        super("remove_lower", "remove_lower {element} \n - удалить из коллекции все элементы, меньшие, чем заданный");
        this.in = in;
        this.shouldPrintHints = shouldPrintHints;
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        Ticket minTicket = new Ticket(new Console(in, shouldPrintHints));
        Iterator<Ticket> iterator = ticketCollection.iterator();
        Ticket t;
        int i = 0;
        while (iterator.hasNext()) {
            t = iterator.next();
            if (t.compareTo(minTicket) < 0) {
                iterator.remove();
                i++;
            }
        }
        System.out.println("Из коллекции удалено " + i + " объектов.");
        if (i > 0)
            ticketCollection.setSaved(false);
    }
}
