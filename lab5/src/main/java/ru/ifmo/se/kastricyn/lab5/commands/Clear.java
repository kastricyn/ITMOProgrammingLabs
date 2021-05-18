package ru.ifmo.se.kastricyn.lab5.commands;

import ru.ifmo.se.kastricyn.lab5.TicketCollection;

/**
 * Команда очистить колекцию
 */
public class Clear extends AbstractCommand {
    private TicketCollection ticketCollection;

    public Clear(TicketCollection ticketCollection) {
        super("clear", "clear \n - очистить коллекцию");
        this.ticketCollection = ticketCollection;
    }

    @Override
    public void execute(String... args) {
        if(!ticketCollection.isEmpty())
            ticketCollection.setSaved(false);
        ticketCollection.clear();
    }
}
