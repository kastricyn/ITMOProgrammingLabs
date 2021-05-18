package ru.ifmo.se.kastricyn.lab5.commands;

import ru.ifmo.se.kastricyn.lab5.TicketCollection;

/**
 * Команда вывести первый элемент коллекции
 */
public class Head extends AbstractCommand {
    private TicketCollection ticketCollection;

    public Head(TicketCollection ticketCollection) {
        super("head", "head \n - вывести первый элемент коллекции");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        System.out.println(ticketCollection.isEmpty() ? "Коллекция пуста" : ticketCollection.peekFirst());
    }
}
