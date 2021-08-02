package ru.ifmo.se.kastricyn.lab8.server;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ifmo.se.kastricyn.lab8.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab8.lib.connection.CommandArgument;
import ru.ifmo.se.kastricyn.lab8.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab8.lib.data.Venue;

/**
 * Содержит нестроковые аргументы для комманд
 */
public class ServerCommandArgument extends CommandArgument {
    private TicketCollection ticketCollection;

    public ServerCommandArgument() {
    }

    public ServerCommandArgument(@NotNull CommandArgument cca) {
        setTicket(cca.getTicket());
        setCommandManager(cca.getCommandManager());
        setVenue(cca.getVenue());
        setUser(cca.getUser());
    }

    @Override
    public @NotNull ServerCommandArgument setCommandManager(CommandManager commandManager) {
        super.setCommandManager(commandManager);
        return this;
    }

    @Override
    public @NotNull ServerCommandArgument setTicket(@Nullable Ticket ticket) {
        super.setTicket(ticket == null ? null : new Ticket(ticket));
        return this;
    }

    @Override
    public @NotNull ServerCommandArgument setVenue(@Nullable Venue venue) {
        super.setVenue(venue == null ? null : new Venue(venue));
        return this;
    }

    public TicketCollection getTicketCollection() {
        return ticketCollection;
    }

    public @NotNull ServerCommandArgument setTicketCollection(TicketCollection ticketCollection) {
        this.ticketCollection = ticketCollection;
        return this;
    }
}
