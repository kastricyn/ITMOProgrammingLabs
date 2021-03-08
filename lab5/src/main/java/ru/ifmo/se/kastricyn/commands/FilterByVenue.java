package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;
import ru.ifmo.se.kastricyn.ticket.Ticket;
import ru.ifmo.se.kastricyn.ticket.Venue;

import java.util.Iterator;
import java.util.Scanner;

public class FilterByVenue extends AbstractCommand {
    private Scanner in;
    private TicketCollection ticketCollection;
    private boolean shouldPrintHints;

    public FilterByVenue(TicketCollection ticketCollection, Scanner in, boolean shouldPrintHints) {
        super("filter_by_venue", "filter_by_venue {venue} \n - вывести элементы, значение поля venue которых равно заданному");
        this.in = in;
        this.shouldPrintHints = shouldPrintHints;
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        Venue venue = Venue.getVenue(in, shouldPrintHints);
        Iterator<Ticket> iterator = ticketCollection.iterator();
        while(iterator.hasNext()){
            Ticket ticket = iterator.next();
            if(venue.equals(ticket.getVenue()))
                System.out.println(ticket);
        }
    }
}