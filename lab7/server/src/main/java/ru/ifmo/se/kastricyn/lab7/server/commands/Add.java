package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.lib.User;
import ru.ifmo.se.kastricyn.lab7.lib.data.Ticket;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

import java.util.ArrayList;

/**
 * Команда добавить эллемент в коллекцию
 */
public class Add extends CommandWithAuth {
    public Add() {
        super("add", "добавить новый элемент в коллекцию");
        ArrayList<Class> argsType = new ArrayList<>();
        setNeedArgumentType(TicketCollection.class, Ticket.class, User.class);
    }


    @Override
    public void execute(String... args) {
        if(!auth())
            return;
        assert objArgs != null;
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        Ticket t = objArgs.getTicket().setUserId(user.getId());
        if(ticketCollection.getDb().addTicket(t)==-1){
            answer = "Добавление не удалось";
            return;
        }
        ticketCollection.add(t);
        answer = "В коллекцю добавлен объект " + t.toString();
        ticketCollection.setSaved(false);
    }

}
