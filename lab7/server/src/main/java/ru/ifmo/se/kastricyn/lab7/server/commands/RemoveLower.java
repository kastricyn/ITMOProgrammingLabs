package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

import java.util.Iterator;

/**
 * Команда удалить из коллекции все элементы, меньшие, чем заданный
 */
public class RemoveLower extends CommandWithAuth {

    public RemoveLower() {
        super("remove_lower", "удалить из коллекции все свои элементы, меньшие, чем заданный");
        setNeedArgumentType(TicketCollection.class, Ticket.class);
    }


    @Override
    public synchronized void execute(String... args) {
        if (!auth())
            return;
        assert objArgs != null;
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        Ticket minTicket = objArgs.getTicket();
        synchronized (ticketCollection) {
            Iterator<Ticket> iterator = ticketCollection.iterator();
            Ticket t;
            int i = 0;
            while (iterator.hasNext()) {
                t = iterator.next();
                if (t.compareTo(minTicket) < 0) {
                    if (ticketCollection.getDb().deleteTicket(t.getId()) && t.getUserId() == objArgs.getUser().getId()) {
                        iterator.remove();
                        i++;
                    }

                }
            }
            answer = "Из коллекции удалено " + i + " объектов.";
        }
    }
}
