package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.server.ServerAbstractCommand;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

/**
 * Команда вывести первый элемент коллекции
 */
public class Head extends ServerAbstractCommand {

    public Head() {
        super("head", "вывести первый элемент коллекции");

        setNeedArgumentType(TicketCollection.class);
    }

    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        answer = ticketCollection.isEmpty() ? "Коллекция пуста" : ticketCollection.peekFirst().toString();
    }
}
