package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

/**
 * Команда вывести первый элемент коллекции
 */
public class Head extends AbstractCommand {

    public Head() {
        super("head", "вывести первый элемент коллекции");

        setArgumentTypes(TicketCollection.class);
    }

    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = (TicketCollection) this.args.get(0);
        answer = ticketCollection.isEmpty() ? "Коллекция пуста" : ticketCollection.peekFirst().toString();
    }
}
