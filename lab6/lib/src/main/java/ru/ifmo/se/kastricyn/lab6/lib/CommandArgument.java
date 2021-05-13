package ru.ifmo.se.kastricyn.lab6.lib;

import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.lib.data.Venue;

/**
 * Содержит нестроковые аргументы для комманд
 */
public class CommandArgument {
    private Ticket ticket;
    private Venue venue;
    private CommandManager commandManager;

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public CommandArgument setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
        return this;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public CommandArgument setTicket(Ticket ticket) {
        this.ticket = ticket;
        return this;
    }

    public Venue getVenue() {
        return venue;
    }

    public CommandArgument setVenue(Venue venue) {
        this.venue = venue;
        return this;
    }
}
