package ru.ifmo.se.kastricyn.lab6.server.commands;

import ru.ifmo.se.kastricyn.lab6.lib.AbstractCommand;
import ru.ifmo.se.kastricyn.lab6.server.TicketCollection;

/**
 * Команда очистить колекцию
 */
public class Clear extends AbstractCommand {

    public Clear() {
        super("clear", "очистить коллекцию");

        setArgumentTypes(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = (TicketCollection) this.args.get(0);
        if (!ticketCollection.isEmpty()) {
            ticketCollection.setSaved(false);
            answer = "Колекция уже пуста";
        } else {
            ticketCollection.clear();
            answer = "Колекция очищена";
        }
    }
}
