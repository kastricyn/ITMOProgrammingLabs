package ru.ifmo.se.kastricyn.lab6.server;

import ru.ifmo.se.kastricyn.lab6.lib.CommandArgument;
import ru.ifmo.se.kastricyn.lab6.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.lib.data.Venue;

/**
 * Содержит нестроковые аргументы для комманд
 */
public class ServerCommandArgument extends CommandArgument {
    private TicketCollection ticketCollection;

    public ServerCommandArgument() {
    }

    public ServerCommandArgument(CommandArgument cca) {
        setTicket(cca.getTicket());
        setCommandManager(cca.getCommandManager());
        setVenue(cca.getVenue());
    }

    @Override
    public ServerCommandArgument setCommandManager(CommandManager commandManager) {
        super.setCommandManager(commandManager);
        return this;
    }

    @Override
    public ServerCommandArgument setTicket(Ticket ticket) {
        super.setTicket(ticket == null ? null : new Ticket(ticket));
        return this;
    }

    @Override
    public ServerCommandArgument setVenue(Venue venue) {
        super.setVenue(venue == null ? null : new Venue(venue));
        return this;
    }

    public TicketCollection getTicketCollection() {
        return ticketCollection;
    }

    public ServerCommandArgument setTicketCollection(TicketCollection ticketCollection) {
        this.ticketCollection = ticketCollection;
        return this;
    }
}
