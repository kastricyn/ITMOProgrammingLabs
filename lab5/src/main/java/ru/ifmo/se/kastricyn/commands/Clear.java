package ru.ifmo.se.kastricyn.commands;

import ru.ifmo.se.kastricyn.TicketCollection;

public class Clear extends AbstractCommand {
    private TicketCollection ticketCollection;

    public Clear(TicketCollection ticketCollection) {
        super("clear", "clear \n - очистить коллекцию");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        ticketCollection.clear();
    }
}
