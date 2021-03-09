package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;
import ru.ifmo.se.kastricyn.ticket.Ticket;

import java.util.Scanner;

public class RemoveLower extends  AbstractCommand{
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
        Ticket minTicket = Ticket.getTicket(in, shouldPrintHints);
        ticketCollection.forEach(t ->{
            if(t.compareTo(minTicket)<0)
                ticketCollection.remove(t.getId());
                //todo change remove by link
        });
    }
}
