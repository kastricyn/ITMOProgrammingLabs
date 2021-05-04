package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.util.ArrayList;

/**
 * Команда добавить эллемент в коллекцию
 */
public class Add extends AbstractCommand {


    public Add() {
        super("add", "добавить новый элемент в коллекцию");
        ArrayList<Class> argsType = new ArrayList<>();

        setArgumentTypes(TicketCollection.class, Ticket.class);
    }


    @Override
    public void execute(String ... args) {
        TicketCollection ticketCollection = (TicketCollection) this.args.get(0);
        Ticket t = (Ticket) this.args.get(1);
        ticketCollection.add(t);
        answer = "В коллекцю добавлен объект " + t.toString();
        ticketCollection.setSaved(false);
    }

}
