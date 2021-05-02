package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.util.stream.StreamSupport;

/**
 * Команда вывести в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show extends AbstractCommand {
    private TicketCollection ticketCollection;

    public Show(TicketCollection ticketCollection) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        if(ticketCollection.isEmpty())
            System.out.println("Коллекция пуста");
        StreamSupport.stream(ticketCollection.spliterator(), true).forEach(System.out::println);
    }
}
