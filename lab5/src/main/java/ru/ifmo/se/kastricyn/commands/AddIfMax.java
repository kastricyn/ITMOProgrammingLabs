package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;
import ru.ifmo.se.kastricyn.ticket.Ticket;

import java.util.Iterator;
import java.util.Scanner;

public class AddIfMax extends AbstractCommand {
    private Scanner in;
    private boolean shouldPrintHints;
    private TicketCollection ticketCollection;

    public AddIfMax(TicketCollection ticketCollection, Scanner in, boolean shouldPrintHints) {
        super("add", "add {element} \n - добавить новый элемент в коллекцию");
        this.in = in;
        this.shouldPrintHints = shouldPrintHints;
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        if (ticketCollection.isEmpty())
            throw new RuntimeException("Коллекция пуста (наибольшего эллемента нет), используйте команду add.");

        //получим наибольший ticket
        Iterator<Ticket> iterator = ticketCollection.iterator();
        Ticket t;
        Ticket maxTicket = Ticket.getTicket(in, shouldPrintHints);
        do {
            t = iterator.next();
            if (t.compareTo(maxTicket) >= 0){
                System.out.println("Коллекция не изменена (данный элемент не больше наибольшего).");
                return;
            }
        } while (iterator.hasNext());
        ticketCollection.add(t);
        System.out.println("В коллекцю добавлен объект " + t);
    }
}
