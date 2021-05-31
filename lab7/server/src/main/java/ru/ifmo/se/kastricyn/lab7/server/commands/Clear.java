package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.server.ServerAbstractCommand;
import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

/**
 * Команда очистить колекцию
 */
public class Clear extends ServerAbstractCommand {

    public Clear() {
        super("clear", "очистить коллекцию");

        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        if (ticketCollection.isEmpty()) {
            answer = "Колекция уже пуста";
        } else {
            ticketCollection.clear();
            answer = "Колекция очищена";
            ticketCollection.setSaved(false);
        }
    }
}
