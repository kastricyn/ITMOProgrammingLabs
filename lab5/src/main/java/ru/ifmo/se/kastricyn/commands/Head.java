package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;

public class Head extends AbstractCommand{
    private TicketCollection ticketCollection;
    public Head(TicketCollection ticketCollection) {
        super("head", "head \n - вывести первый элемент коллекции");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        System.out.println(ticketCollection.peekFirst());
    }
}
