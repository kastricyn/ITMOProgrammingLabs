package ru.ifmo.se.kastricyn.lab7.lib.connection;

import ru.ifmo.se.kastricyn.lab7.lib.CommandManager;
import ru.ifmo.se.kastricyn.lab7.lib.User;
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
    protected User user;

    public User getUser() {
        return user;
    }

    public CommandArgument setUser(User user) {
        this.user = user;
        return this;
    }

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
    public String toString() {
        return "CommandArgument{" +
                "ticket=" + ticket +
                ", venue=" + venue +
                ", commandManager=" + commandManager +
                ", user=" + user +
                '}';
    }
}
