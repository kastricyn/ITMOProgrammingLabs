package ru.ifmo.se.kastricyn.lab8.server.commands;

import ru.ifmo.se.kastricyn.lab8.server.TicketCollection;

/**
 * Команда очистить колекцию
 */
public class Clear extends CommandWithAuth {

    public Clear() {
        super("clear", "удалить все свои элементы");

        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public synchronized void execute(String... args) {
        if (!auth())
            return;
        assert objArgs != null;
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        if (!ticketCollection.getDb().clear(objArgs.getUser().getId())) {
            answer = "Очистка не удалась";
            return;
        }
        ticketCollection.clear(objArgs.getUser().getId());
        answer = "Элементы ползователя с id=" + objArgs.getUser().getId() + " удалены.";

    }
}
