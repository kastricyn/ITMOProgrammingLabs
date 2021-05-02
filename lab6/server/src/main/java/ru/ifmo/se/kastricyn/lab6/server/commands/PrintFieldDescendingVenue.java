package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.util.Comparator;
import java.util.stream.StreamSupport;

/**
 * Команда вывести значения поля venue всех элементов в порядке убывания
 */
public class PrintFieldDescendingVenue extends AbstractCommand {
    private TicketCollection ticketCollection;

    public PrintFieldDescendingVenue(TicketCollection ticketCollection) {
        super("print_field_descending_venue", "вывести значения поля venue всех элементов в порядке убывания");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        StreamSupport.stream(ticketCollection.spliterator(), true)
                .map(Ticket::getVenue)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEachOrdered(System.out::println);
    }
}
