package ru.ifmo.se.kastricyn.lab7.lib;

import org.jetbrains.annotations.NotNull;
import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.lib.data.Venue;

import java.io.Serializable;

/**
 * Содержит нестроковые аргументы для комманд
 */
public class CommandArgument implements Serializable {
    protected Ticket ticket;
    protected Venue venue;
    protected CommandManager commandManager;

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

    @Override
    public @NotNull String toString() {
        return "CommandArgument{" +
                "ticket=" + ticket +
                ", venue=" + venue +
                ", commandManager=" + commandManager +
                '}';
    }
}
