package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;
import ru.ifmo.se.kastricyn.data.Ticket;
import ru.ifmo.se.kastricyn.data.Venue;
import ru.ifmo.se.kastricyn.utility.Console;

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
        Venue venue = new Venue(new Console(in, shouldPrintHints)); //todo change all commands in, shouldPrintHints -> CONSOLE
        Iterator<Ticket> iterator = ticketCollection.iterator();
        boolean flag = true;
        while(iterator.hasNext()){
            Ticket ticket = iterator.next();
            if(venue.equals(ticket.getVenue())) {
                if(flag){
                    flag = false;
                    System.out.println("Элементы имеющие введённый venue:");
                }
                System.out.println(ticket);
            }
            if(flag)
                System.out.println("Нет элементов имеющие введённый venue.");
        }
    }
}
