package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

/**
 * Команда очистить колекцию
 */
public class Clear extends AbstractCommand {
    private TicketCollection ticketCollection;

    public Clear(TicketCollection ticketCollection) {
        super("clear", "очистить коллекцию");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        if(!ticketCollection.isEmpty())
            ticketCollection.setSaved(false);
        ticketCollection.clear();
    }
}
