package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

import java.util.Iterator;

/**
 * Команда удалить из коллекции все элементы, меньшие, чем заданный
 */
public class RemoveLower extends AbstractCommand {

    public RemoveLower() {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный");
        setArgumentTypes(TicketCollection.class, Ticket.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = (TicketCollection) this.args.get(0);
        Ticket minTicket = (Ticket) this.args.get(1);
        Iterator<Ticket> iterator = ticketCollection.iterator();
        Ticket t;
        int i = 0;
        while (iterator.hasNext()) {
            t = iterator.next();
            if (t.compareTo(minTicket) < 0) {
                iterator.remove();
                i++;
            }
        }
        answer = "Из коллекции удалено " + i + " объектов.";
        if (i > 0)
            ticketCollection.setSaved(false);
    }
}
