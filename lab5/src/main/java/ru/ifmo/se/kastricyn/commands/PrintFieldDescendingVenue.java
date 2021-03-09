package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;
import ru.ifmo.se.kastricyn.ticket.Venue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PrintFieldDescendingVenue extends AbstractCommand{
    private  TicketCollection ticketCollection;
    public PrintFieldDescendingVenue(TicketCollection ticketCollection) {
        super("print_field_descending_venue", "print_field_descending_venue - \n вывести значения поля venue всех элементов в порядке убывания");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        LinkedList<Venue> venues = new LinkedList<>();
        ticketCollection.forEach((t) ->venues.add(t.getVenue()));
        Collections.sort(venues);
        venues.forEach(System.out::println);
    }
}
