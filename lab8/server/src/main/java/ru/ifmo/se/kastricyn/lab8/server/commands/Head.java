package ru.ifmo.se.kastricyn.lab8.server.commands;

import ru.ifmo.se.kastricyn.lab8.server.TicketCollection;

/**
 * Команда вывести первый элемент коллекции
 */
public class Head extends CommandWithAuth {

    public Head() {
        super("head", "вывести первый элемент коллекции");

        setNeedArgumentType(TicketCollection.class);
    }

    @Override
    public synchronized void execute(String... args) {
        if (!auth())
            return;
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        synchronized (ticketCollection) {
            answer = ticketCollection.isEmpty() ? "Коллекция пуста" :
                    ticketCollection.peekFirst().toString();
        }
    }
}
