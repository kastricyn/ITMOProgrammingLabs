package ru.ifmo.se.kastricyn.lab7.server.commands;

import ru.ifmo.se.kastricyn.lab7.server.TicketCollection;

/**
 * Команда очистить колекцию
 */
public class Clear extends CommandWithAuth {

    public Clear() {
        super("clear", "удалить все свои элементы");

        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public void execute(String... args) {
        if (!auth())
            return;
        TicketCollection ticketCollection = objArgs.getTicketCollection();
        if (!ticketCollection.getDb().clear(user.getId())) {
            answer = "Очистка не удалась";
            return;
        }
        ticketCollection.clear(user.getId());
        answer = "Элементы ползователя с id=" + user.getId()+ " удалены.";
        ticketCollection.setSaved(false);

    }
}
