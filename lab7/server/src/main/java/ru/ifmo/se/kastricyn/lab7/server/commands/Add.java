package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.server.ServerAbstractCommand;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

import java.util.ArrayList;

/**
 * Команда добавить эллемент в коллекцию
 */
public class Add extends ServerAbstractCommand {
    public Add() {
        super("add", "добавить новый элемент в коллекцию");
        ArrayList<Class> argsType = new ArrayList<>();
        setNeedArgumentType(TicketCollection.class, Ticket.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        Ticket t = objArgs.getTicket();
        ticketCollection.add(t);
        answer = "В коллекцю добавлен объект " + t.toString();
        ticketCollection.setSaved(false);
    }

}
