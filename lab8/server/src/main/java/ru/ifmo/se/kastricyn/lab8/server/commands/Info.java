package ru.ifmo.se.kastricyn.lab8.server.commands;

import ru.ifmo.se.kastricyn.lab8.server.TicketCollection;

/**
 * Команда вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
 */
public class Info extends CommandWithAuth {

    public Info() {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");

        setNeedArgumentType(TicketCollection.class);
    }


    @Override
    public synchronized void execute(String... args) {
        if (!auth())
            return;
        TicketCollection ticketCollection = objArgs.getTicketCollection();

        answer = "Информация о коллекции:\n"
                + "тип: Ticket" + "\n"
                + "дата инициализации: " + ticketCollection.getInitDate() + "\n"
                + "кол-во элементов: " + ticketCollection.size() + "\n";
    }
}
